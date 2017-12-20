package com.github.jleskovar.btcrpc

import com.googlecode.jsonrpc4j.JsonRpcMethod
import java.math.BigDecimal
import java.util.concurrent.Future

/**
 * Created by james on 1/12/17.
 */
interface AsyncBitcoinRpcClient {

    @JsonRpcMethod("abandontransaction")
    fun abandonTransaction(transactionId: String)

    @JsonRpcMethod("abortrescan")
    fun abortRescan()

    @JsonRpcMethod("addmultisigaddress")
    fun addMultiSigAddress(required: Int? = null, keys: List<String>): Future<String>

    @JsonRpcMethod("addnode")
    fun addNode(address: String, operation: NodeListOperation)

    @JsonRpcMethod("backupwallet")
    fun backupWallet(destination: String)

    @JsonRpcMethod("clearbanned")
    fun clearBanned()

    @JsonRpcMethod("createmultisig")
    fun createMultiSig(required: Int, keys: List<String>): Future<MultiSigAddress>

    @JsonRpcMethod("createrawtransaction")
    fun createRawTransaction(
            inputs: List<OutPoint>,
            outputs: Map<String, BigDecimal>,
            lockTime: Int? = null,
            replaceable: Boolean? = null
    ): Future<String>

    @JsonRpcMethod("decoderawtransaction")
    fun decodeRawTransaction(transactionId: String): Future<Transaction>

    @JsonRpcMethod("decodescript")
    fun decodeScript(scriptHex: String): Future<DecodedScript>

    @JsonRpcMethod("disconnectnode")
    fun disconnectNode(nodeAddress: String? = null, nodeId: Int? = null)

    @JsonRpcMethod("dumpprivkey")
    fun dumpPrivateKey(address: String): Future<String>

    @JsonRpcMethod("dumpwallet")
    fun dumpWallet(filename: String): Future<Map<*, *>>

    @JsonRpcMethod("encryptwallet")
    fun encryptWallet(passphrase: String)

    @JsonRpcMethod("generate")
    fun generate(numberOfBlocks: Int, maxTries: Int? = null): Future<List<String>>

    @JsonRpcMethod("getaddednodeinfo")
    fun getAddedNodeInfo(): Future<List<AddedNodeInfo>>

    @JsonRpcMethod("getbalance")
    fun getBalance(
            account: String = "*",
            minconf: Int = 1,
            includeWatchOnly: Boolean = false): Future<BigDecimal>

    @JsonRpcMethod("getbestblockhash")
    fun getBestBlockhash(): Future<String>

    @JsonRpcMethod("getblock")
    fun getBlockData(blockHash: String, verbosity: Int = 0): Future<String>

    @JsonRpcMethod("getblock")
    fun getBlock(blockHash: String, verbosity: Int = 1): Future<BlockInfo>

    @JsonRpcMethod("getblock")
    fun getBlockWithTransactions(blockHash: String, verbosity: Int = 2): Future<BlockInfoWithTransactions>

    @JsonRpcMethod("getblockchaininfo")
    fun getBlockchainInfo(): Future<BlockChainInfo>

    @JsonRpcMethod("getblockcount")
    fun getBlockCount(): Future<Int>

    @JsonRpcMethod("getblockhash")
    fun getBlockHash(height: Int): Future<String>

    @JsonRpcMethod("getblockheader")
    fun getBlockHeader(blockHash: String, verbose: Boolean? = false): Future<Any>

    @JsonRpcMethod("getblocktemplate")
    fun getBlockTemplate(blockTemplateRequest: BlockTemplateRequest? = null)

    @JsonRpcMethod("getchaintips")
    fun getChainTips(): Future<List<ChainTip>>

    @JsonRpcMethod("getchaintxstats")
    fun getChainTransactionStats(
            blockWindowSize: Int? = null,
            blockHashEnd: String? = null
    ): Future<ChainTransactionStats>

    @JsonRpcMethod("getconnectioncount")
    fun getConnectionCount(): Future<Int>

    @JsonRpcMethod("getdifficulty")
    fun getDifficulty(): Future<BigDecimal>

    @JsonRpcMethod("getmemoryinfo")
    fun getMemoryInfo(): Future<Any>

    @JsonRpcMethod("getmempoolancestors")
    fun getMempoolAncestors(transactionId: String): Future<Any>

    @JsonRpcMethod("getmempooldescendants")
    fun getMempoolDescendants(): Future<Any>

    @JsonRpcMethod("getmempoolentry")
    fun getMempoolEntry(transactionId: String): Future<Map<*, *>>

    @JsonRpcMethod("getmempoolinfo")
    fun getMempoolInfo(): Future<MemPoolInfo>

    @JsonRpcMethod("getmininginfo")
    fun getMiningInfo(): Future<MiningInfo>

    @JsonRpcMethod("getnettotals")
    fun getNetworkTotals(): Future<NetworkTotals>

    @JsonRpcMethod("getnetworkhashps")
    fun getNetworkHashesPerSeconds(lastBlocks: Int, height: Int): Future<Long>

    @JsonRpcMethod("getnetworkinfo")
    fun getNetworkInfo(): Future<NetworkInfo>

    @JsonRpcMethod("getnewaddress")
    fun getNewAddress(): Future<String>

    @JsonRpcMethod("getpeerinfo")
    fun getPeerInfo(): Future<List<PeerInfo>>

    @JsonRpcMethod("getrawchangeaddress")
    fun getRawChangeAddress(): Future<String>

    @JsonRpcMethod("getrawmempool")
    fun getRawMemPool(verbose: Boolean = false): Future<List<Map<*, *>>>

    @JsonRpcMethod("getrawtransaction")
    fun getRawTransaction(transactionId: String): Future<Transaction>

    @JsonRpcMethod("getreceivedbyaddress")
    fun getReceivedByAddress(address: String, minConfirmations: Int = 1): Future<BigDecimal>

    @JsonRpcMethod("gettransaction")
    fun getWalletTransaction(transactionId: String): Future<Map<*, *>>

    @JsonRpcMethod("gettxout")
    fun getUnspentTransactionOutputInfo(transactionId: String, index: Int): Future<Map<*, *>>

    @JsonRpcMethod("gettxoutsetinfo")
    fun getUnspentTransactionOutputSetInfo(): Future<UtxoSet>

    @JsonRpcMethod("getwalletinfo")
    fun getWalletInfo(): Future<Map<*, *>>

    @JsonRpcMethod("importaddress")
    fun importAddress(
            scriptOrAddress: String,
            label: String? = null,
            rescan: Boolean? = null,
            includePayToScriptHash: Boolean? = null)

    @JsonRpcMethod("importprivkey")
    fun importPrivateKey(
            privateKey: String,
            label: String? = null,
            rescan: Boolean? = null
    )

    @JsonRpcMethod("importpubkey")
    fun importPublicKey(
            publicKey: String,
            label: String? = null,
            rescan: Boolean? = null
    )

    @JsonRpcMethod("importwallet")
    fun importWallet(walletFile: String)

    @JsonRpcMethod("keypoolrefill")
    fun keypoolRefill(newSize: Int = 100)

    @JsonRpcMethod("listaddressgroupings")
    fun listAddressGroupings(): Future<List<*>>

    @JsonRpcMethod("listbanned")
    fun listBanned(): Future<List<String>>

    @JsonRpcMethod("listlockunspent")
    fun listLockUnspent(): Future<List<Map<*, *>>>

    @JsonRpcMethod("listreceivedbyaddress")
    fun listReceivedByAddress(
            minConfirmations: Int? = null,
            includeEmpty: Boolean? = null,
            includeWatchOnly: Boolean? = null
    ): Future<List<Map<*, *>>>

    @JsonRpcMethod("listsinceblock")
    fun listSinceBlock(
            blockHash: String? = null,
            targetConfirmations: Int? = null,
            includeWatchOnly: Boolean? = null,
            includeRemoved: Boolean? = null
    ): Future<Map<*, *>>

    @JsonRpcMethod("listtransactions")
    fun listTransactions(
            account: String? = null,
            count: Int? = null,
            skip: Int? = null,
            includeWatchOnly: Boolean? = null
    ): Future<List<Map<*, *>>>

    @JsonRpcMethod("listunspent")
    fun listUnspent(
            minConfirmations: Int? = null,
            maxConfirmations: Int? = null,
            addresses: List<String>? = null,
            includeUnsafe: Boolean? = null,
            queryOptions: QueryOptions? = null
    ): Future<QueryResult>

    @JsonRpcMethod("listwallets")
    fun listWallets(): Future<List<String>>

    @JsonRpcMethod("lockunspent")
    fun lockUnspent(unlock: Boolean, unspentOutputs: List<OutPoint>): Future<Boolean>

    fun ping()

    @JsonRpcMethod("preciousblock")
    fun preciousBlock(block: String)

    @JsonRpcMethod("prioritisetransaction")
    fun prioritiseTransaction(transactionId: String, dummy: Int, feeDeltaSatoshis: Int)

    @JsonRpcMethod("pruneblockchain")
    fun pruneBlockchain(blockHeightOrUnixTimestamp: Long)

    @JsonRpcMethod("removeprunedfunds")
    fun removePrunedFunds(transactionId: String)

    @JsonRpcMethod("sendmany")
    fun sendMany(account: String,
                 addressAmounts: Map<String, BigDecimal>,
                 comment: String? = null,
                 subtractFee: Boolean = false,
                 replaceable: Boolean = false,
                 minConfirmations: Int? = null,
                 feeEstimateMode: FeeEstimateMode? = null)

    @JsonRpcMethod("sendrawtransaction")
    fun sendRawTransaction(transaction: String)

    @JsonRpcMethod("sendtoaddress")
    fun sendToAddress(
            address: String,
            amount: BigDecimal,
            comment: String? = null,
            commentTo: String? = null,
            subtractFee: Boolean? = null,
            replaceable: Boolean? = null,
            minConfirmations: Int? = null,
            feeEstimateMode: FeeEstimateMode? = null): Future<String>

    @JsonRpcMethod("setban")
    fun setBan(
            address: String,
            operation: NodeListOperation,
            seconds: Int
    )

    @JsonRpcMethod("settxfee")
    fun setTransactionFee(fee: Double)

    @JsonRpcMethod("signmessage")
    fun signMessage(
            address: String,
            message: String
    )

    @JsonRpcMethod("signmessagewithprivkey")
    fun signMessageWithPrivateKey(
            privateKey: String,
            message: String
    )

    @JsonRpcMethod("signrawtransaction")
    fun signRawTransaction(transactionId: String)

    @JsonRpcMethod("submitblock")
    fun submitBlock(blockData: String)

    fun uptime(): Future<Int>

    @JsonRpcMethod("validateaddress")
    fun validateAddress(address: String)

    @JsonRpcMethod("verifychain")
    fun verifyChain()

    @JsonRpcMethod("verifymessage")
    fun verifyMessage(
            address: String,
            signature: String,
            message: String
    )

    @JsonRpcMethod("searchrawtransactions")
    fun searchRawSerialisedTransactions(
            address: String,
            verbose: Int?=0,
            skip: Int?=null,
            count: Int?=null,
            vInExtra: Int?=null,
            reverse: Boolean?=null): Future<List<String>>

    @JsonRpcMethod("searchrawtransactions")
    fun searchRawVerboseTransactions(
            address: String,
            verbose: Int?=1,
            skip: Int?=null,
            count: Int?=null,
            vInExtra: Int?=null,
            reverse: Boolean?=null): Future<List<SearchedTransactionResult>>

    /**
     * btcd-specific extension methods
     */
    @JsonRpcMethod("authenticate")
    fun btcdAuthenticate(username: String, password: String)

    @JsonRpcMethod("generate")
    fun btcdGenerate(numberOfBlocks: Int): Future<List<String>>

    @JsonRpcMethod("getblock")
    fun btcdGetBlockWithTransactions(blockHash: String, verbose: Boolean = true): Future<String>
}
