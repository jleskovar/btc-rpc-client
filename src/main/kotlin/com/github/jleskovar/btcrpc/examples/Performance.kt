package com.github.jleskovar.btcrpc.examples

import com.github.jleskovar.btcrpc.BitcoinRpcClient
import com.github.jleskovar.btcrpc.BitcoinRpcClientFactory

/**
 * Created by james on 17/12/17.
 */
fun main(args: Array<String>) {

    val address = "n2hcSwULcFVpR7XZD6o83A7Ckvtohvb43t"
    val privateKey = "cN88TB4Frqcb9S5hb889Y2HqrY3Wq5XSBwHTkWgxD48dCb9ippG8"

    val webSocketClient = BitcoinRpcClientFactory.createClient("james", "james", "localhost", 18334, true, webSocket = true)
    val httpClient = BitcoinRpcClientFactory.createClient("james", "james", "localhost", 18334, true, webSocket = false)

    // Generate 1000 blocks
    httpClient.generateBtcd(1000)

    doPerfTestBlock("http", httpClient)
    doPerfTestBlock("ws", webSocketClient)

}

fun doPerfTestBlock(clientName: String, client: BitcoinRpcClient) {
    println("Starting retrieval of first 1000 blocks for ${clientName} client..")
    val startTime = System.currentTimeMillis()
    for (i in 0..1000) {
        client.getBlockHash(i)
    }
    println("${clientName} client took ${System.currentTimeMillis() - startTime} ms")
}