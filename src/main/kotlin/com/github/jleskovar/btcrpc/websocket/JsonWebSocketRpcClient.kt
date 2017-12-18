package com.github.jleskovar.btcrpc.websocket

import com.googlecode.jsonrpc4j.IJsonRpcClient
import com.googlecode.jsonrpc4j.JsonRpcClient
import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketAdapter
import com.neovisionaries.ws.client.WebSocketFactory
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.lang.reflect.Type
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext

class JsonWebSocketRpcClient(wsUrl: String, user: String, password: String, sslContext: SSLContext) : JsonRpcClient(), IJsonRpcClient {

    private val webSocketFactory = WebSocketFactory()
    private val socket: WebSocket
    val responses = mutableMapOf<String, BlockingQueue<String>>()

    init {
        webSocketFactory.sslContext = sslContext
        socket = webSocketFactory.createSocket(wsUrl)

        val objectMapper = objectMapper

        socket.addListener(object : WebSocketAdapter() {
            override fun onTextMessage(websocket: WebSocket?, text: String?) {
                val id = objectMapper.readTree(text)["id"].asText()
                responses[id]?.put(text)
            }
        })

        socket.connect()

        // invoke authenticate method as soon as websocket is open (btcd)
        socket.sendText("""{"jsonrpc":"2.0","id":"1","method":"authenticate","params":["$user","$password"]}""")
    }

    override fun invoke(methodName: String?, argument: Any?, returnType: Type?, extraHeaders: MutableMap<String, String>?): Any {
        val outputStream = ByteArrayOutputStream()
        super.invoke(methodName, argument, outputStream)

        val sentOutput = outputStream.toString()
        val id = this.objectMapper.readTree(sentOutput)["id"].asText()

        val queue = ArrayBlockingQueue<String>(1)
        responses[id] = queue
        socket.sendText(sentOutput)

        val response = queue.poll(5000, TimeUnit.MILLISECONDS) // Parameterise this
        return super.readResponse(returnType, ByteArrayInputStream(response.toByteArray()))
    }

    override fun invoke(methodName: String?, argument: Any?) {
        invoke(methodName, argument, null as Type?)
    }

    override fun invoke(methodName: String?, argument: Any?, returnType: Type?): Any {
        return invoke(methodName, argument, returnType, mutableMapOf())
    }

    override fun <T : Any?> invoke(methodName: String?, argument: Any?, clazz: Class<T>?): T {
        return invoke(methodName, argument, clazz, mutableMapOf())
    }

    override fun <T : Any?> invoke(methodName: String?, argument: Any?, clazz: Class<T>?, extraHeaders: MutableMap<String, String>?): T {
        return invoke(methodName, argument, clazz as Type, extraHeaders) as T
    }

}