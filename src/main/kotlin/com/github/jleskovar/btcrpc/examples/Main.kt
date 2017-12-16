package com.github.jleskovar.btcrpc.examples

import com.github.jleskovar.btcrpc.BitcoinRpcClientFactory

fun main(args: Array<String>) {
    val rpcClient = BitcoinRpcClientFactory.createClient("james", "", "localhost", 8334, true)
    println(rpcClient.getBlockCount())
}