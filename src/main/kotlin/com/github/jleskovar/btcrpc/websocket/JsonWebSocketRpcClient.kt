package com.github.jleskovar.btcrpc.websocket

import com.googlecode.jsonrpc4j.IJsonRpcClient
import com.googlecode.jsonrpc4j.JsonRpcClient
import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketAdapter
import com.neovisionaries.ws.client.WebSocketFactory
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.lang.reflect.Type
import java.util.concurrent.BlockingQueue
import java.util.concurrent.SynchronousQueue
import javax.net.ssl.SSLContext

class JsonWebSocketRpcClient(wsUrl: String, sslContext: SSLContext) : JsonRpcClient(), IJsonRpcClient {

    private val webSocketFactory = WebSocketFactory()
    private val socket: WebSocket
    private val responses = mutableMapOf<String, BlockingQueue<String>>()

    init {
        webSocketFactory.sslContext = sslContext
        socket = webSocketFactory.createSocket(wsUrl)

        socket.addListener(object : WebSocketAdapter() {
            override fun onTextMessage(websocket: WebSocket?, text: String?) {
                val id = fastExtractId(text!!)
                responses[id]?.put(text)
            }
        })
    }

    fun connect() {
        socket.connect()
    }

    fun disconnect() {
        socket.disconnect()
    }

    override fun invoke(methodName: String?, argument: Any?, returnType: Type?, extraHeaders: MutableMap<String, String>?): Any? {
        val outputStream = ByteArrayOutputStream()
        super.invoke(methodName, argument, outputStream)
        val output = outputStream.toString()

        val id = fastExtractId(output)
        val queue = SynchronousQueue<String>()

        try {
            responses[id] = queue
            socket.sendText(output)
            val response = queue.take()
            return if (response == null) null else super.readResponse(returnType, ByteArrayInputStream(response.toByteArray()))
        } finally {
            responses.remove(id)
        }
    }

    private fun fastExtractId(jsonRpc: String) = jsonRpc.substringAfterLast("\"id\":\"").substringBefore("\"")

    override fun invoke(methodName: String?, argument: Any?) {
        invoke(methodName, argument, null as Type?)
    }

    override fun invoke(methodName: String?, argument: Any?, returnType: Type?): Any? {
        return invoke(methodName, argument, returnType, mutableMapOf())
    }

    override fun <T : Any?> invoke(methodName: String?, argument: Any?, clazz: Class<T>?): T {
        return invoke(methodName, argument, clazz, mutableMapOf())
    }

    override fun <T : Any?> invoke(methodName: String?, argument: Any?, clazz: Class<T>?, extraHeaders: MutableMap<String, String>?): T {
        return invoke(methodName, argument, clazz as Type, extraHeaders) as T
    }

}