package com.github.jleskovar.btcrpc.websocket

import com.github.jleskovar.btcrpc.BitcoinRpcClient

interface WebSocketBitcoinRpcClient : BitcoinRpcClient {
    fun connect()
    fun disconnect()
}