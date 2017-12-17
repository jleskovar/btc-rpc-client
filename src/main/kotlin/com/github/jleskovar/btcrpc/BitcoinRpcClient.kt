package com.github.jleskovar.btcrpc

import com.googlecode.jsonrpc4j.JsonRpcMethod
import java.math.BigDecimal

/**
 * Created by james on 1/12/17.
 */
interface BitcoinRpcClient {

    @JsonRpcMethod("abandontransaction")
    fun abandonTransaction(transactionId: String)

    @JsonRpcMethod("abortrescan")
    fun abortRescan()

    @JsonRpcMethod("addmultisigaddress")
    fun addMultiSigAddress(required: Int? = null, keys: List<String>): String

    enum class NodeListOperation { onetry, add, remove }

    @JsonRpcMethod("addnode")
    fun addNode(address: String, operation: NodeListOperation)

    @JsonRpcMethod("backupwallet")
    fun backupWallet(destination: String)

    @JsonRpcMethod("clearbanned")
    fun clearBanned()

    @JsonRpcMethod("createmultisig")
    fun createMultiSig(required: Int, keys: List<String>): MultiSigAddress

    @JsonRpcMethod("createrawtransaction")
    fun createRawTransaction(
            inputs: List<OutPoint>,
            outputs: Map<String, BigDecimal>,
            lockTime: Int? = null,
            replaceable: Boolean? = null
    ): String

    @JsonRpcMethod("decoderawtransaction")
    fun decodeRawTransaction(transactionId: String): Transaction

    @JsonRpcMethod("decodescript")
    fun decodeScript(scriptHex: String): DecodedScript

    @JsonRpcMethod("disconnectnode")
    fun disconnectNode(nodeAddress: String? = null, nodeId: Int? = null)

    @JsonRpcMethod("dumpprivkey")
    fun dumpPrivateKey(address: String): String

    @JsonRpcMethod("dumpwallet")
    fun dumpWallet(filename: String): Map<*, *>

    @JsonRpcMethod("encryptwallet")
    fun encryptWallet(passphrase: String)

    @JsonRpcMethod("generate")
    fun generate(numberOfBlocks: Int, maxTries: Int? = null): List<String>

    @JsonRpcMethod("getaddednodeinfo")
    fun getAddedNodeInfo(): List<AddedNodeInfo>

    @JsonRpcMethod("getbalance")
    fun getBalance(
            account: String = "*",
            minconf: Int = 1,
            includeWatchOnly: Boolean = false): BigDecimal

    @JsonRpcMethod("getbestblockhash")
    fun getBestBlockhash(): String

    @JsonRpcMethod("getblock")
    fun getBlockData(blockHash: String, verbosity: Int = 0): String

    @JsonRpcMethod("getblock")
    fun getBlock(blockHash: String, verbosity: Int = 1): BlockInfo

    @JsonRpcMethod("getblock")
    fun getBlockWithTransactionsBtcd(blockHash: String, verbose: Boolean = true): String // BTCD expects verbose flag

    @JsonRpcMethod("getblock")
    fun getBlockWithTransactions(blockHash: String, verbosity: Int = 2): BlockInfoWithTransactions

    @JsonRpcMethod("getblockchaininfo")
    fun getBlockchainInfo(): BlockChainInfo

    @JsonRpcMethod("getblockcount")
    fun getBlockCount(): Int

    @JsonRpcMethod("getblockhash")
    fun getBlockHash(height: Int): String

    @JsonRpcMethod("getblockheader")
    fun getBlockHeader(blockHash: String, verbose: Boolean? = false): Any

    @JsonRpcMethod("getblocktemplate")
    fun getBlockTemplate(blockTemplateRequest: BlockTemplateRequest? = null)

    @JsonRpcMethod("getchaintips")
    fun getChainTips(): List<ChainTip>

    @JsonRpcMethod("getchaintxstats")
    fun getChainTransactionStats(
            blockWindowSize: Int? = null,
            blockHashEnd: String? = null
    ): ChainTransactionStats

    @JsonRpcMethod("getconnectioncount")
    fun getConnectionCount(): Int

    @JsonRpcMethod("getdifficulty")
    fun getDifficulty(): BigDecimal

    @JsonRpcMethod("getmemoryinfo")
    fun getMemoryInfo(): Any

    @JsonRpcMethod("getmempoolancestors")
    fun getMempoolAncestors(transactionId: String): Any

    @JsonRpcMethod("getmempooldescendants")
    fun getMempoolDescendants(): Any

    @JsonRpcMethod("getmempoolentry")
    fun getMempoolEntry(transactionId: String): Map<*, *>

    @JsonRpcMethod("getmempoolinfo")
    fun getMempoolInfo(): MemPoolInfo

    @JsonRpcMethod("getmininginfo")
    fun getMiningInfo(): MiningInfo

    @JsonRpcMethod("getnettotals")
    fun getNetworkTotals(): NetworkTotals

    @JsonRpcMethod("getnetworkhashps")
    fun getNetworkHashesPerSeconds(lastBlocks: Int, height: Int): Long

    @JsonRpcMethod("getnetworkinfo")
    fun getNetworkInfo(): NetworkInfo

    @JsonRpcMethod("getnewaddress")
    fun getNewAddress(): String

    @JsonRpcMethod("getpeerinfo")
    fun getPeerInfo(): List<PeerInfo>

    @JsonRpcMethod("getrawchangeaddress")
    fun getRawChangeAddress(): String

    @JsonRpcMethod("getrawmempool")
    fun getRawMemPool(verbose: Boolean = false): List<Map<*, *>>

    @JsonRpcMethod("getrawtransaction")
    fun getRawTransaction(transactionId: String): Transaction

    @JsonRpcMethod("getreceivedbyaddress")
    fun getReceivedByAddress(address: String, minConfirmations: Int = 1): BigDecimal

    @JsonRpcMethod("gettransaction")
    fun getWalletTransaction(transactionId: String): Map<*, *>

    @JsonRpcMethod("gettxout")
    fun getUnspentTransactionOutputInfo(transactionId: String, index: Int): Map<*, *>

    @JsonRpcMethod("gettxoutsetinfo")
    fun getUnspentTransactionOutputSetInfo(): UtxoSet

    @JsonRpcMethod("getwalletinfo")
    fun getWalletInfo(): Map<*, *>

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
    fun listAddressGroupings(): List<*>

    @JsonRpcMethod("listbanned")
    fun listBanned(): List<String>

    @JsonRpcMethod("listlockunspent")
    fun listLockUnspent(): List<Map<*, *>>

    @JsonRpcMethod("listreceivedbyaddress")
    fun listReceivedByAddress(
            minConfirmations: Int? = null,
            includeEmpty: Boolean? = null,
            includeWatchOnly: Boolean? = null
    ): List<Map<*, *>>

    @JsonRpcMethod("listsinceblock")
    fun listSinceBlock(
            blockHash: String? = null,
            targetConfirmations: Int? = null,
            includeWatchOnly: Boolean? = null,
            includeRemoved: Boolean? = null
    ): Map<*, *>

    @JsonRpcMethod("listtransactions")
    fun listTransactions(
            account: String? = null,
            count: Int? = null,
            skip: Int? = null,
            includeWatchOnly: Boolean? = null
    ): List<Map<*, *>>

    @JsonRpcMethod("listunspent")
    fun listUnspent(
            minConfirmations: Int? = null,
            maxConfirmations: Int? = null,
            addresses: List<String>? = null,
            includeUnsafe: Boolean? = null,
            queryOptions: QueryOptions? = null
    ): QueryResult

    @JsonRpcMethod("listwallets")
    fun listWallets(): List<String>

    @JsonRpcMethod("lockunspent")
    fun lockUnspent(unlock: Boolean, unspentOutputs: List<OutPoint>): Boolean

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
            feeEstimateMode: FeeEstimateMode? = null): String

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

    fun uptime(): Int

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
            reverse: Boolean?=null): List<String>

    @JsonRpcMethod("searchrawtransactions")
    fun searchRawVerboseTransactions(
            address: String,
            verbose: Int?=1,
            skip: Int?=null,
            count: Int?=null,
            vInExtra: Int?=null,
            reverse: Boolean?=null): List<SearchedTransactionResult>


}
