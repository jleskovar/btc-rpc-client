package com.github.jleskovar.btcrpc

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
                     webSocket: Boolean = false,
                     sslContext: SSLContext = createUnsafeSslContext()):

            BitcoinRpcClient {

        val jsonRpcHttpClient = createRpcClient(secure, user, host, port, password, webSocket, sslContext)

        return ProxyUtil.createClientProxy(
                BitcoinRpcClientFactory::class.java.classLoader,
                BitcoinRpcClient::class.java,
                jsonRpcHttpClient
        )
    }

    private fun createRpcClient(secure: Boolean, user: String, host: String, port: Int, password: String, webSocket: Boolean, sslContext: SSLContext): IJsonRpcClient {
        val jsonRpcHttpClient: IJsonRpcClient

        if (webSocket) {
            jsonRpcHttpClient = JsonWebSocketRpcClient(
                    "${if (secure) "wss" else "ws"}://$host:$port/ws",
                    user, password, sslContext)
        } else {
            jsonRpcHttpClient = JsonRpcHttpClient(
                    URL("${if (secure) "https" else "http"}://$user@$host:$port"),
                    mapOf(Pair("Authorization", computeBasicAuth(user, password))))

            jsonRpcHttpClient.setSslContext(sslContext)
        }

        return jsonRpcHttpClient
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

