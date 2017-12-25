package com.github.jleskovar.btcrpc.examples

import com.github.jleskovar.btcrpc.AsyncBitcoinRpcClient
import com.github.jleskovar.btcrpc.BitcoinRpcClientFactory
import com.github.jleskovar.btcrpc.BlockInfoWithTransactions
import com.github.jleskovar.btcrpc.Transaction
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by james on 25/12/17.
 */
class BlockWalker(val client: AsyncBitcoinRpcClient, val numWorkers: Int = 8) {
    private val workCount = AtomicInteger(0)

    fun getBlocks(): Observable<BlockInfoWithTransactions> {
        return adviseObservable( Observable
            // Defer the future to allow Rx to schedule onto IO pool
            .defer { Observable.fromFuture(client.getBlockchainInfo()) }
            .flatMap { Observable.range(0, it.blocks!!.toInt()) }
            .flatMap { Observable.fromFuture(client.getBlockHash(it)) }
            .flatMap { Observable.fromFuture(client.btcdGetBlockWithTransactions(it)) }
        )
    }

    fun getTransactions(): Observable<Transaction> {
        return getBlocks().flatMap { Observable.fromIterable(it.rawtx) }
    }

    private fun <T : Any?> adviseObservable(o: Observable<T>): Observable<T> {
        return o.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .groupBy({ workCount.getAndIncrement() % numWorkers })
                .flatMap { it.observeOn(Schedulers.computation()) }
    }
}

fun main(args: Array<String>) {
    val webSocketClient = BitcoinRpcClientFactory.createAsyncWsClient("james", "james", "localhost", 18334, true)

    webSocketClient.connect()

    val blockWalker = BlockWalker(webSocketClient, numWorkers = 2)

    blockWalker
            .getTransactions()
            .subscribe { println("THR[${Thread.currentThread().name}] ${it.hash} - ${it.confirmations}") }

    Thread.sleep(1000)

    webSocketClient.disconnect()
}
