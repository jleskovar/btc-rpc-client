package com.github.jleskovar.btcrpc

import com.googlecode.jsonrpc4j.JsonRpcMethod
import java.math.BigDecimal
import java.util.concurrent.CompletableFuture

/**
 * Created by james on 1/12/17.
 */
interface AsyncBitcoinRpcClient {

    @JsonRpcMethod("abandontransaction")
    fun abandonTransaction(transactionId: String): CompletableFuture<Void>

    @JsonRpcMethod("abortrescan")
    fun abortRescan(): CompletableFuture<Void>

    @JsonRpcMethod("addmultisigaddress")
    fun addMultiSigAddress(required: Int? = null, keys: List<String>): CompletableFuture<String>

    @JsonRpcMethod("addnode")
    fun addNode(address: String, operation: NodeListOperation): CompletableFuture<Void>

    @JsonRpcMethod("backupwallet")
    fun backupWallet(destination: String): CompletableFuture<Void>

    @JsonRpcMethod("clearbanned")
    fun clearBanned(): CompletableFuture<Void>

    @JsonRpcMethod("createmultisig")
    fun createMultiSig(required: Int, keys: List<String>): CompletableFuture<MultiSigAddress>

    @JsonRpcMethod("createrawtransaction")
    fun createRawTransaction(
            inputs: List<OutPoint>,
            outputs: Map<String, BigDecimal>,
            lockTime: Int? = null,
            replaceable: Boolean? = null
    ): CompletableFuture<String>

    @JsonRpcMethod("decoderawtransaction")
    fun decodeRawTransaction(transactionId: String): CompletableFuture<Transaction>

    @JsonRpcMethod("decodescript")
    fun decodeScript(scriptHex: String): CompletableFuture<DecodedScript>

    @JsonRpcMethod("disconnectnode")
    fun disconnectNode(nodeAddress: String? = null, nodeId: Int? = null): CompletableFuture<Void>

    @JsonRpcMethod("dumpprivkey")
    fun dumpPrivateKey(address: String): CompletableFuture<String>

    @JsonRpcMethod("dumpwallet")
    fun dumpWallet(filename: String): CompletableFuture<Map<*, *>>

    @JsonRpcMethod("encryptwallet")
    fun encryptWallet(passphrase: String): CompletableFuture<Void>

    @JsonRpcMethod("generate")
    fun generate(numberOfBlocks: Int, maxTries: Int? = null): CompletableFuture<List<String>>

    @JsonRpcMethod("getaddednodeinfo")
    fun getAddedNodeInfo(): CompletableFuture<List<AddedNodeInfo>>

    @JsonRpcMethod("getbalance")
    fun getBalance(
            account: String = "*",
            minconf: Int = 1,
            includeWatchOnly: Boolean = false): CompletableFuture<BigDecimal>

    @JsonRpcMethod("getbestblockhash")
    fun getBestBlockhash(): CompletableFuture<String>

    @JsonRpcMethod("getblock")
    fun getBlockData(blockHash: String, verbosity: Int = 0): CompletableFuture<String>

    @JsonRpcMethod("getblock")
    fun getBlock(blockHash: String, verbosity: Int = 1): CompletableFuture<BlockInfo>

    @JsonRpcMethod("getblock")
    fun getBlockWithTransactions(blockHash: String, verbosity: Int = 2): CompletableFuture<BlockInfoWithTransactions>

    @JsonRpcMethod("getblockchaininfo")
    fun getBlockchainInfo(): CompletableFuture<BlockChainInfo>

    @JsonRpcMethod("getblockcount")
    fun getBlockCount(): CompletableFuture<Int>

    @JsonRpcMethod("getblockhash")
    fun getBlockHash(height: Int): CompletableFuture<String>

    @JsonRpcMethod("getblockheader")
    fun getBlockHeader(blockHash: String, verbose: Boolean? = false): CompletableFuture<Any>

    @JsonRpcMethod("getblocktemplate")
    fun getBlockTemplate(blockTemplateRequest: BlockTemplateRequest? = null): CompletableFuture<Void>

    @JsonRpcMethod("getchaintips")
    fun getChainTips(): CompletableFuture<List<ChainTip>>

    @JsonRpcMethod("getchaintxstats")
    fun getChainTransactionStats(
            blockWindowSize: Int? = null,
            blockHashEnd: String? = null
    ): CompletableFuture<ChainTransactionStats>

    @JsonRpcMethod("getconnectioncount")
    fun getConnectionCount(): CompletableFuture<Int>

    @JsonRpcMethod("getdifficulty")
    fun getDifficulty(): CompletableFuture<BigDecimal>

    @JsonRpcMethod("getmemoryinfo")
    fun getMemoryInfo(): CompletableFuture<Any>

    @JsonRpcMethod("getmempoolancestors")
    fun getMempoolAncestors(transactionId: String): CompletableFuture<Any>

    @JsonRpcMethod("getmempooldescendants")
    fun getMempoolDescendants(): CompletableFuture<Any>

    @JsonRpcMethod("getmempoolentry")
    fun getMempoolEntry(transactionId: String): CompletableFuture<Map<*, *>>

    @JsonRpcMethod("getmempoolinfo")
    fun getMempoolInfo(): CompletableFuture<MemPoolInfo>

    @JsonRpcMethod("getmininginfo")
    fun getMiningInfo(): CompletableFuture<MiningInfo>

    @JsonRpcMethod("getnettotals")
    fun getNetworkTotals(): CompletableFuture<NetworkTotals>

    @JsonRpcMethod("getnetworkhashps")
    fun getNetworkHashesPerSeconds(lastBlocks: Int, height: Int): CompletableFuture<Long>

    @JsonRpcMethod("getnetworkinfo")
    fun getNetworkInfo(): CompletableFuture<NetworkInfo>

    @JsonRpcMethod("getnewaddress")
    fun getNewAddress(): CompletableFuture<String>

    @JsonRpcMethod("getpeerinfo")
    fun getPeerInfo(): CompletableFuture<List<PeerInfo>>

    @JsonRpcMethod("getrawchangeaddress")
    fun getRawChangeAddress(): CompletableFuture<String>

    @JsonRpcMethod("getrawmempool")
    fun getRawMemPool(verbose: Boolean = false): CompletableFuture<List<Map<*, *>>>

    @JsonRpcMethod("getrawtransaction")
    fun getRawTransaction(transactionId: String, verbosity: Int = 1): CompletableFuture<Transaction>

    @JsonRpcMethod("getreceivedbyaddress")
    fun getReceivedByAddress(address: String, minConfirmations: Int = 1): CompletableFuture<BigDecimal>

    @JsonRpcMethod("gettransaction")
    fun getWalletTransaction(transactionId: String): CompletableFuture<Map<*, *>>

    @JsonRpcMethod("gettxout")
    fun getUnspentTransactionOutputInfo(transactionId: String, index: Int): CompletableFuture<Map<*, *>>

    @JsonRpcMethod("gettxoutsetinfo")
    fun getUnspentTransactionOutputSetInfo(): CompletableFuture<UtxoSet>

    @JsonRpcMethod("getwalletinfo")
    fun getWalletInfo(): CompletableFuture<Map<*, *>>

    @JsonRpcMethod("importaddress")
    fun importAddress(
            scriptOrAddress: String,
            label: String? = null,
            rescan: Boolean? = null,
            includePayToScriptHash: Boolean? = null
    ): CompletableFuture<Void>

    @JsonRpcMethod("importprivkey")
    fun importPrivateKey(
            privateKey: String,
            label: String? = null,
            rescan: Boolean? = null
    ): CompletableFuture<Void>

    @JsonRpcMethod("importpubkey")
    fun importPublicKey(
            publicKey: String,
            label: String? = null,
            rescan: Boolean? = null
    ): CompletableFuture<Void>

    @JsonRpcMethod("importwallet")
    fun importWallet(walletFile: String): CompletableFuture<Void>

    @JsonRpcMethod("keypoolrefill")
    fun keypoolRefill(newSize: Int = 100): CompletableFuture<Void>

    @JsonRpcMethod("listaddressgroupings")
    fun listAddressGroupings(): CompletableFuture<List<*>>

    @JsonRpcMethod("listbanned")
    fun listBanned(): CompletableFuture<List<String>>

    @JsonRpcMethod("listlockunspent")
    fun listLockUnspent(): CompletableFuture<List<Map<*, *>>>

    @JsonRpcMethod("listreceivedbyaddress")
    fun listReceivedByAddress(
            minConfirmations: Int? = null,
            includeEmpty: Boolean? = null,
            includeWatchOnly: Boolean? = null
    ): CompletableFuture<List<Map<*, *>>>

    @JsonRpcMethod("listsinceblock")
    fun listSinceBlock(
            blockHash: String? = null,
            targetConfirmations: Int? = null,
            includeWatchOnly: Boolean? = null,
            includeRemoved: Boolean? = null
    ): CompletableFuture<Map<*, *>>

    @JsonRpcMethod("listtransactions")
    fun listTransactions(
            account: String? = null,
            count: Int? = null,
            skip: Int? = null,
            includeWatchOnly: Boolean? = null
    ): CompletableFuture<List<Map<*, *>>>

    @JsonRpcMethod("listunspent")
    fun listUnspent(
            minConfirmations: Int? = null,
            maxConfirmations: Int? = null,
            addresses: List<String>? = null,
            includeUnsafe: Boolean? = null,
            queryOptions: QueryOptions? = null
    ): CompletableFuture<List<QueryResult>>

    @JsonRpcMethod("listwallets")
    fun listWallets(): CompletableFuture<List<String>>

    @JsonRpcMethod("lockunspent")
    fun lockUnspent(unlock: Boolean, unspentOutputs: List<OutPoint>): CompletableFuture<Boolean>

    fun ping(): CompletableFuture<Void>

    @JsonRpcMethod("preciousblock")
    fun preciousBlock(block: String): CompletableFuture<Void>

    @JsonRpcMethod("prioritisetransaction")
    fun prioritiseTransaction(transactionId: String, dummy: Int, feeDeltaSatoshis: Int): CompletableFuture<Void>

    @JsonRpcMethod("pruneblockchain")
    fun pruneBlockchain(blockHeightOrUnixTimestamp: Long): CompletableFuture<Void>

    @JsonRpcMethod("removeprunedfunds")
    fun removePrunedFunds(transactionId: String): CompletableFuture<Void>

    @JsonRpcMethod("sendmany")
    fun sendMany(account: String,
                 addressAmounts: Map<String, BigDecimal>,
                 comment: String? = null,
                 subtractFee: Boolean = false,
                 replaceable: Boolean = false,
                 minConfirmations: Int? = null,
                 feeEstimateMode: FeeEstimateMode? = null
    ): CompletableFuture<Void>

    @JsonRpcMethod("sendrawtransaction")
    fun sendRawTransaction(transaction: String): CompletableFuture<String>

    @JsonRpcMethod("sendtoaddress")
    fun sendToAddress(
            address: String,
            amount: BigDecimal,
            comment: String? = null,
            commentTo: String? = null,
            subtractFee: Boolean? = null,
            replaceable: Boolean? = null,
            minConfirmations: Int? = null,
            feeEstimateMode: FeeEstimateMode? = null): CompletableFuture<String>

    @JsonRpcMethod("setban")
    fun setBan(
            address: String,
            operation: NodeListOperation,
            seconds: Int
    ): CompletableFuture<Void>

    @JsonRpcMethod("settxfee")
    fun setTransactionFee(fee: Double): CompletableFuture<Void>

    @JsonRpcMethod("estimatesmartfee")
    fun estimateSmartFee(confTarget: Int, feeEstimateMode: FeeEstimateMode? = FeeEstimateMode.CONSERVATIVE): CompletableFuture<EstimateSmartFee>

    @JsonRpcMethod("signmessage")
    fun signMessage(
            address: String,
            message: String
    ): CompletableFuture<Void>

    @JsonRpcMethod("signmessagewithprivkey")
    fun signMessageWithPrivateKey(
            privateKey: String,
            message: String
    ): CompletableFuture<Void>

    @JsonRpcMethod("signrawtransaction")
    fun signRawTransaction(transactionId: String): CompletableFuture<Void>

    @JsonRpcMethod("submitblock")
    fun submitBlock(blockData: String): CompletableFuture<Void>

    fun uptime(): CompletableFuture<Int>

    @JsonRpcMethod("validateaddress")
    fun validateAddress(address: String): CompletableFuture<Void>

    @JsonRpcMethod("verifychain")
    fun verifyChain(): CompletableFuture<Boolean>

    @JsonRpcMethod("verifymessage")
    fun verifyMessage(
            address: String,
            signature: String,
            message: String
    ): CompletableFuture<Void>

    @JsonRpcMethod("searchrawtransactions")
    fun searchRawSerialisedTransactions(
            address: String,
            verbose: Int? = 0,
            skip: Int? = null,
            count: Int? = null,
            vInExtra: Int? = null,
            reverse: Boolean? = null): CompletableFuture<List<String>>

    @JsonRpcMethod("searchrawtransactions")
    fun searchRawVerboseTransactions(
            address: String,
            verbose: Int? = 1,
            skip: Int? = null,
            count: Int? = null,
            vInExtra: Int? = null,
            reverse: Boolean? = null): CompletableFuture<List<SearchedTransactionResult>>

    /**
     * btcd-specific extension methods
     */
    @JsonRpcMethod("authenticate")
    fun btcdAuthenticate(username: String, password: String): CompletableFuture<Void>

    @JsonRpcMethod("generate")
    fun btcdGenerate(numberOfBlocks: Int): CompletableFuture<List<String>>

    @JsonRpcMethod("getblock")
    fun btcdGetBlockWithTransactions(blockHash: String, verbose: Boolean = true, verboseTx: Boolean = true): CompletableFuture<BlockInfoWithTransactions>
}
