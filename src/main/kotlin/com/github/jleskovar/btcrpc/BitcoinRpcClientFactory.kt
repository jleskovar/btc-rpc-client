package com.github.jleskovar.btcrpc

import com.github.jleskovar.btcrpc.websocket.*
import com.googlecode.jsonrpc4j.IJsonRpcClient
import com.googlecode.jsonrpc4j.JsonRpcHttpClient
import com.googlecode.jsonrpc4j.ProxyUtil
import java.net.URL
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.*
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * Created by james on 3/12/17.
 */
object BitcoinRpcClientFactory {
    @JvmStatic
    fun createClient(user: String,
                     password: String,
                     host: String,
                     port: Int,
                     secure: Boolean = false,
                     sslContext: SSLContext = createUnsafeSslContext()):

            BitcoinRpcClient {

        val jsonRpcHttpClient: IJsonRpcClient

        jsonRpcHttpClient = JsonRpcHttpClient(
                URL("${if (secure) "https" else "http"}://$user@$host:$port"),
                mapOf(Pair("Authorization", computeBasicAuth(user, password))))

        jsonRpcHttpClient.setSslContext(sslContext)

        return ProxyUtil.createClientProxy(
                BitcoinRpcClientFactory::class.java.classLoader,
                BitcoinRpcClient::class.java,
                jsonRpcHttpClient
        )
    }

    @JvmStatic
    fun createWsClient(user: String,
                       password: String,
                       host: String,
                       port: Int,
                       secure: Boolean = false,
                       sslContext: SSLContext = createUnsafeSslContext()):

            WebSocketBitcoinRpcClient {

        val jsonWebSocketRpcClient = JsonWebSocketRpcClient(
                wsUrl = "${if (secure) "wss" else "ws"}://$host:$port/ws",
                sslContext = sslContext
        )

        val proxyClient = ProxyUtil.createClientProxy(
                BitcoinRpcClientFactory::class.java.classLoader,
                BitcoinRpcClient::class.java,
                jsonWebSocketRpcClient
        )

        return WrappedWebSocketBtcClient(user, password, proxyClient, jsonWebSocketRpcClient)
    }

    @JvmStatic
    fun createAsyncWsClient(user: String,
                       password: String,
                       host: String,
                       port: Int,
                       secure: Boolean = false,
                       sslContext: SSLContext = createUnsafeSslContext()):

            AsyncWebSocketBitcoinRpcClient {

        val jsonWebSocketRpcClient = JsonAsyncWebSocketRpcClient(
                wsUrl = "${if (secure) "wss" else "ws"}://$host:$port/ws",
                sslContext = sslContext
        )

        val proxyClient = ProxyUtil.createClientProxy(
                BitcoinRpcClientFactory::class.java.classLoader,
                AsyncBitcoinRpcClient::class.java,
                jsonWebSocketRpcClient
        )

        return WrappedAsyncWebSocketBtcClient(user, password, proxyClient, jsonWebSocketRpcClient)
    }

    private fun createUnsafeSslContext(): SSLContext {
        val dummyTrustManager = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {}
            override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {}
            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        })

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, dummyTrustManager, SecureRandom())
        return sslContext
    }

    private fun computeBasicAuth(user: String, password: String) =
            "Basic ${BASE64.encodeToString("$user:$password".toByteArray())}"

    private val BASE64 = Base64.getEncoder()
}

