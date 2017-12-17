package com.github.jleskovar.btcrpc.examples

import com.github.jleskovar.btcrpc.BitcoinRpcClientFactory

fun main(args: Array<String>) {
    val rpcClient = BitcoinRpcClientFactory.createClient(
            user = "james",
            password = "james",
            host = "james-machine",
            port = 8334,
            secure = true)

    var start = System.currentTimeMillis()
    for (i in 0..rpcClient.getBlockCount()) {
        rpcClient.getBlockHash(i)
        if (i % 100 == 0) println(i)
    }
    println("took ${System.currentTimeMillis() - start} ms")
}