package com.github.jleskovar.btcrpc.examples

import com.github.jleskovar.btcrpc.BitcoinRpcClientFactory

/**
 * Created by james on 17/12/17.
 */
fun main(args: Array<String>) {
    val client = BitcoinRpcClientFactory.createClient("james", "james", "localhost", 18334, true, true)
//    val client = BitcoinRpcClientFactory.createClient("james", "james", "james-machine", 8334, true, true)
    println(client.getBlockCount())
}