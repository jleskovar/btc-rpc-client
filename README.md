# btc-rpc-client
Kotlin-based JSON-RPC client for bitcoind/btcd. Requires `jsonrpc4j` (and optionally `nv-websocket-client` for WebSocket support)

## Features
 * Supports all of the non-deprecated RPC methods exposed by the JSON-RPC interface in bitcoind and btcd
 * Data classes defined for all data types
 * Supports SSL (btcd only)
 * Experimental support for WebSocket interface (btcd only)

## Usage

    // Create a secure HTTP JSON-RPC Client to a local node
    // NOTE: SSL supported only in btcd
    val rpcClient = BitcoinRpcClientFactory.createClient(
            user = "james",
            password = "",
            host = "localhost",
            port = 8334,
            secure = true,
            webSocket = false)
            
    println(rpcClient.getBlockCount())

## Maven
    <dependency>
        <groupId>com.github.jleskovar</groupId>
        <artifactId>btc-rpc-client</artifactId>
        <version>1.0.0</version>
    </dependency>

## Notes

Should be compatible with bitcoind and btcd JSON-RPC interfaces, for methods implemented in btcd.
 * The default SSL mode is insecure, as it does not validate certificate paths. It is highly recommended a validating SSL context is created with appropriate truststores configured, by passing in the `sslContext` parameter to `createClient`
 * The websocket RPC interface in btcd offers superior performance compared to HTTP-based JSON-RPC, and is recommended for applications requiring heavy usage of the JSON-RPC interface (block iteration, transaction / address lookup, etc). On a 2016 macbook pro, running btcd in regtest mode:
 
        // Performance.kt
        Starting retrieval of first 100 blocks using http client..
        http client took 4071 ms (24 requests/sec)
        Starting retrieval of first 100 blocks using ws client..
        ws client took 59 ms (1694 requests/sec)

#### Donation
Thanks! [bitcoin:17bxks7R353xniuuTkbsEXwwqFn96vr2X7](bitcoin:17bxks7R353xniuuTkbsEXwwqFn96vr2X7?label=beer%20fund)
