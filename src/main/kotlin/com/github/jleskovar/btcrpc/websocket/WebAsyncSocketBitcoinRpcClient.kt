package com.github.jleskovar.btcrpc.websocket

import com.github.jleskovar.btcrpc.AsyncBitcoinRpcClient

interface AsyncWebSocketBitcoinRpcClient : AsyncBitcoinRpcClient {
    fun connect()
    fun disconnect()
}