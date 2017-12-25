package com.github.jleskovar.btcrpc.examples

import com.github.jleskovar.btcrpc.AsyncBitcoinRpcClient
import com.github.jleskovar.btcrpc.BitcoinRpcClientFactory
import com.github.jleskovar.btcrpc.BlockInfoWithTransactions
import com.github.jleskovar.btcrpc.Transaction
import io.reactivex.Observable

/**
 * Created by james on 25/12/17.
 */
class BlockWalker(val client: AsyncBitcoinRpcClient) {
    fun getBlocks(): Observable<BlockInfoWithTransactions> {
        return Observable
                .fromFuture(client.getBlockchainInfo())
                .flatMap { Observable.range(0, it.blocks!!.toInt()) }
                .flatMap { Observable.fromFuture(client.getBlockHash(it)) }
                .flatMap { Observable.fromFuture(client.btcdGetBlockWithTransactions(it)) }
    }

    fun getTransactions(): Observable<Transaction> {
        return getBlocks().flatMap { Observable.fromIterable(it.rawtx) }
    }
}

fun main(args: Array<String>) {
    val webSocketClient = BitcoinRpcClientFactory.createAsyncWsClient("james", "james", "localhost", 18334, true)
    webSocketClient.connect()

    val blockWalker = BlockWalker(webSocketClient)
    blockWalker.getTransactions().subscribe { println(it.hash) }

    webSocketClient.disconnect()
}
