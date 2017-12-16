package com.github.jleskovar.btcrpc.examples

import com.github.jleskovar.btcrpc.BitcoinRpcClientFactory

fun main(args: Array<String>) {
    val rpcClient = BitcoinRpcClientFactory.createClient(
            user = "james",
            password = "",
            host = "localhost",
            port = 8334,
            secure = true)
    println(rpcClient.getBlockCount())
}