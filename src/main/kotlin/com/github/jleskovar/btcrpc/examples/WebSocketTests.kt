package com.github.jleskovar.btcrpc.examples

import com.github.jleskovar.btcrpc.BitcoinRpcClientFactory

/**
 * Created by james on 17/12/17.
 */
fun main(args: Array<String>) {

    val webSocketClient = BitcoinRpcClientFactory.createWsClient("james", "james", "localhost", 18334, true)

    webSocketClient.connect()
    println(webSocketClient.getBlockCount())
    webSocketClient.disconnect()
}