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
            secure = true)
            
    println(rpcClient.getBlockCount())
            
    // OR: Create a WebSocket JSON-RPC client (btcd only)
    val rpcClient = BitcoinRpcClientFactory.createWsClient(
            user = "james",
            password = "james",
            host = "localhost",
            port = 18334,
            secure = true

    rpcClient.connect() // WS Clients must be explicitly connected/disconnected
    println(rpcClient.getBlockCount())
    rpcClient.disconnect()
           

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
        Starting retrieval of first 1000 blocks using http client..
        http client took 30867 ms (32 requests/sec)
        Starting retrieval of first 1000 blocks using ws client..
        ws client took 250 ms (4000 requests/sec)
        
 * Repeated runs of the above test (retrieving first 10000 blocks repeatedly) have yielded an average of ~8000 rps

#### Donation
Thanks! [bitcoin:17bxks7R353xniuuTkbsEXwwqFn96vr2X7](bitcoin:17bxks7R353xniuuTkbsEXwwqFn96vr2X7?label=beer%20fund)
