package com.github.jleskovar.btcrpc.examples

import com.github.jleskovar.btcrpc.AsyncBitcoinRpcClient
import com.github.jleskovar.btcrpc.BitcoinRpcClientFactory
import com.github.jleskovar.btcrpc.BlockInfoWithTransactions
import com.github.jleskovar.btcrpc.Transaction
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by james on 25/12/17.
 */
class BlockWalker(val client: AsyncBitcoinRpcClient, val startingBlockHeight: Int = 0) {

    fun getBlocks(): Observable<BlockInfoWithTransactions> {
        return Observable
                // Defer the future to allow Rx to schedule onto IO pool
                .defer { Observable.fromFuture(client.getBlockchainInfo()) }
                .flatMap { Observable.range(startingBlockHeight, it.blocks!!.toInt()) }
                .flatMap { Observable.fromFuture(client.getBlockHash(it)) }
                .flatMap { Observable.fromFuture(client.btcdGetBlockWithTransactions(it)) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
    }

    fun getTransactions(): Observable<Transaction> {
        return getBlocks()
                .flatMap { Observable.fromIterable(it.rawtx) }
    }
}

fun main(args: Array<String>) {
    val webSocketClient = BitcoinRpcClientFactory.createAsyncWsClient("james", "james", "localhost", 18334, true)

    webSocketClient.connect()

    val blockWalker = BlockWalker(webSocketClient)

    blockWalker
            .getTransactions()
            .groupBy { it.time!! % 8 }
            .flatMap { it
                    .observeOn(Schedulers.computation())
                    .map { println("${System.currentTimeMillis()} THR[${Thread.currentThread().name}] ${it.hash} - ${it.confirmations}"); Thread.sleep(500); it }
            }
            .forEach { println("${System.currentTimeMillis()} ${Thread.currentThread().name}  ${it.hash}") }

    Thread.sleep(10000)

    webSocketClient.disconnect()
}
