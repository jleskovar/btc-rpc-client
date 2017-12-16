# btc-rpc-client
Kotlin-based JSON-RPC client for bitcoind/btcd. Requires `jsonrpc4j`

## Usage

    // Create a secure JSON-RPC Client to a local node
    // NOTE: SSL supported only in btcd
    val rpcClient = BitcoinRpcClientFactory.createClient(
            user = "james",
            password = "",
            host = "localhost",
            port = 8334,
            secure = true)
            
    println(rpcClient.getBlockCount())

## Notes

Should be compatible with bitcoind and btcd JSON-RPC interfaces, for methods implemented in btcd.
 * SSL mode is insecure, as it does not validate certificate paths. Not recommended for production usage. 

#### Donation
Thanks! [bitcoin:17bxks7R353xniuuTkbsEXwwqFn96vr2X7](bitcoin:17bxks7R353xniuuTkbsEXwwqFn96vr2X7?label=beer%20fund)
