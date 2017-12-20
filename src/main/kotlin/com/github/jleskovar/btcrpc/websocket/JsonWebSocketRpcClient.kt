package com.github.jleskovar.btcrpc.websocket

import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketAdapter
import com.neovisionaries.ws.client.WebSocketListener
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.lang.reflect.Type
import java.util.concurrent.CompletableFuture
import javax.net.ssl.SSLContext

class JsonWebSocketRpcClient(wsUrl: String, sslContext: SSLContext) : AbstractJsonWebSocketRpcClient(wsUrl, sslContext) {
    private val responses = mutableMapOf<String, CompletableFuture<String>>()

    override fun handleTextMessage(text: String?) {
        val id = fastExtractId(text!!)
        responses[id]?.complete(text)
        responses.remove(id)
    }

    override fun invoke(methodName: String?, argument: Any?, returnType: Type?, extraHeaders: MutableMap<String, String>?): Any? {
        val outputStream = ByteArrayOutputStream()
        super.invoke(methodName, argument, outputStream)
        val output = outputStream.toString()

        val id = fastExtractId(output)

        try {
            val completableFuture = CompletableFuture<String>()
            responses[id] = completableFuture
            socket.sendText(output)
            val response = completableFuture.get()
            return if (response == null) null else super.readResponse(returnType, ByteArrayInputStream(response.toByteArray()))
        } finally {
            responses.remove(id)
        }
    }


}