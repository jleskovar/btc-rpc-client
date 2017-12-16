package com.github.jleskovar.btcrpc

import com.googlecode.jsonrpc4j.JsonRpcHttpClient
import com.googlecode.jsonrpc4j.ProxyUtil
import java.net.URL
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
    fun createClient(user: String, password: String, host: String, port: Int, secure: Boolean = false): BitcoinRpcClient {
        val dummyTrustManager = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) { }
            override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) { }
            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        })

        val jsonRpcHttpClient = JsonRpcHttpClient(
                URL("${if (secure) "https" else "http"}://$user@$host:$port"),
                mapOf(Pair("Authorization", computeBasicAuth(user, password))))

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, dummyTrustManager, java.security.SecureRandom())
        jsonRpcHttpClient.setSslContext(sslContext)

        return ProxyUtil.createClientProxy(
                BitcoinRpcClientFactory::class.java.classLoader,
                BitcoinRpcClient::class.java,
                jsonRpcHttpClient
        )
    }

    private fun computeBasicAuth(user: String, password: String) =
            "Basic ${BASE64.encodeToString("$user:$password".toByteArray())}"

    private val BASE64 = Base64.getEncoder()
}