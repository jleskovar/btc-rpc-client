package com.github.jleskovar.btcrpc.websocket

import com.github.jleskovar.btcrpc.*
import java.math.BigDecimal

class WrappedWebSocketBtcClient(val user: String, val password: String, val delegate: BitcoinRpcClient, val jsonWebSocketRpcClient: JsonWebSocketRpcClient) : WebSocketBitcoinRpcClient {

    override fun connect() {
        jsonWebSocketRpcClient.connect()
        // Authenticate as soon as web socket is open (btcd)
        jsonWebSocketRpcClient.invoke("authenticate", listOf(user, password))
    }

    override fun disconnect() {
        jsonWebSocketRpcClient.disconnect()
    }

    override fun abandonTransaction(transactionId: String) {
        delegate.abandonTransaction(transactionId)
    }

    override fun abortRescan() {
        delegate.abortRescan()
    }

    override fun addMultiSigAddress(required: Int?, keys: List<String>): String {
        return delegate.addMultiSigAddress(required, keys)
    }

    override fun addNode(address: String, operation: BitcoinRpcClient.NodeListOperation) {
        delegate.addNode(address, operation)
    }

    override fun backupWallet(destination: String) {
        delegate.backupWallet(destination)
    }

    override fun clearBanned() {
        delegate.clearBanned()
    }

    override fun createMultiSig(required: Int, keys: List<String>): MultiSigAddress {
        return delegate.createMultiSig(required, keys)
    }

    override fun createRawTransaction(inputs: List<OutPoint>, outputs: Map<String, BigDecimal>, lockTime: Int?, replaceable: Boolean?): String {
        return delegate.createRawTransaction(inputs, outputs, lockTime, replaceable)
    }

    override fun decodeRawTransaction(transactionId: String): Transaction {
        return delegate.decodeRawTransaction(transactionId)
    }

    override fun decodeScript(scriptHex: String): DecodedScript {
        return delegate.decodeScript(scriptHex)
    }

    override fun disconnectNode(nodeAddress: String?, nodeId: Int?) {
        delegate.disconnectNode(nodeAddress, nodeId)
    }

    override fun dumpPrivateKey(address: String): String {
        return delegate.dumpPrivateKey(address)
    }

    override fun dumpWallet(filename: String): Map<*, *> {
        return delegate.dumpWallet(filename)
    }

    override fun encryptWallet(passphrase: String) {
        delegate.encryptWallet(passphrase)
    }

    override fun generate(numberOfBlocks: Int, maxTries: Int?): List<String> {
        return delegate.generate(numberOfBlocks, maxTries)
    }

    override fun generateBtcd(numberOfBlocks: Int): List<String> {
        return delegate.generateBtcd(numberOfBlocks)
    }

    override fun getAddedNodeInfo(): List<AddedNodeInfo> {
        return delegate.getAddedNodeInfo()
    }

    override fun getBalance(account: String, minconf: Int, includeWatchOnly: Boolean): BigDecimal {
        return delegate.getBalance(account, minconf, includeWatchOnly)
    }

    override fun getBestBlockhash(): String {
        return delegate.getBestBlockhash()
    }

    override fun getBlockData(blockHash: String, verbosity: Int): String {
        return delegate.getBlockData(blockHash, verbosity)
    }

    override fun getBlock(blockHash: String, verbosity: Int): BlockInfo {
        return delegate.getBlock(blockHash, verbosity)
    }

    override fun getBlockWithTransactionsBtcd(blockHash: String, verbose: Boolean): String {
        return delegate.getBlockWithTransactionsBtcd(blockHash, verbose)
    }

    override fun getBlockWithTransactions(blockHash: String, verbosity: Int): BlockInfoWithTransactions {
        return delegate.getBlockWithTransactions(blockHash, verbosity)
    }

    override fun getBlockchainInfo(): BlockChainInfo {
        return delegate.getBlockchainInfo()
    }

    override fun getBlockCount(): Int {
        return delegate.getBlockCount()
    }

    override fun getBlockHash(height: Int): String {
        return delegate.getBlockHash(height)
    }

    override fun getBlockHeader(blockHash: String, verbose: Boolean?): Any {
        return delegate.getBlockHeader(blockHash, verbose)
    }

    override fun getBlockTemplate(blockTemplateRequest: BlockTemplateRequest?) {
        delegate.getBlockTemplate(blockTemplateRequest)
    }

    override fun getChainTips(): List<ChainTip> {
        return delegate.getChainTips()
    }

    override fun getChainTransactionStats(blockWindowSize: Int?, blockHashEnd: String?): ChainTransactionStats {
        return delegate.getChainTransactionStats(blockWindowSize, blockHashEnd)
    }

    override fun getConnectionCount(): Int {
        return delegate.getConnectionCount()
    }

    override fun getDifficulty(): BigDecimal {
        return delegate.getDifficulty()
    }

    override fun getMemoryInfo(): Any {
        return delegate.getMemoryInfo()
    }

    override fun getMempoolAncestors(transactionId: String): Any {
        return delegate.getMempoolAncestors(transactionId)
    }

    override fun getMempoolDescendants(): Any {
        return delegate.getMempoolDescendants()
    }

    override fun getMempoolEntry(transactionId: String): Map<*, *> {
        return delegate.getMempoolEntry(transactionId)
    }

    override fun getMempoolInfo(): MemPoolInfo {
        return delegate.getMempoolInfo()
    }

    override fun getMiningInfo(): MiningInfo {
        return delegate.getMiningInfo()
    }

    override fun getNetworkTotals(): NetworkTotals {
        return delegate.getNetworkTotals()
    }

    override fun getNetworkHashesPerSeconds(lastBlocks: Int, height: Int): Long {
        return delegate.getNetworkHashesPerSeconds(lastBlocks, height)
    }

    override fun getNetworkInfo(): NetworkInfo {
        return delegate.getNetworkInfo()
    }

    override fun getNewAddress(): String {
        return delegate.getNewAddress()
    }

    override fun getPeerInfo(): List<PeerInfo> {
        return delegate.getPeerInfo()
    }

    override fun getRawChangeAddress(): String {
        return delegate.getRawChangeAddress()
    }

    override fun getRawMemPool(verbose: Boolean): List<Map<*, *>> {
        return delegate.getRawMemPool(verbose)
    }

    override fun getRawTransaction(transactionId: String): Transaction {
        return delegate.getRawTransaction(transactionId)
    }

    override fun getReceivedByAddress(address: String, minConfirmations: Int): BigDecimal {
        return delegate.getReceivedByAddress(address, minConfirmations)
    }

    override fun getWalletTransaction(transactionId: String): Map<*, *> {
        return delegate.getWalletTransaction(transactionId)
    }

    override fun getUnspentTransactionOutputInfo(transactionId: String, index: Int): Map<*, *> {
        return delegate.getUnspentTransactionOutputInfo(transactionId, index)
    }

    override fun getUnspentTransactionOutputSetInfo(): UtxoSet {
        return delegate.getUnspentTransactionOutputSetInfo()
    }

    override fun getWalletInfo(): Map<*, *> {
        return delegate.getWalletInfo()
    }

    override fun importAddress(scriptOrAddress: String, label: String?, rescan: Boolean?, includePayToScriptHash: Boolean?) {
        delegate.importAddress(scriptOrAddress, label, rescan, includePayToScriptHash)
    }

    override fun importPrivateKey(privateKey: String, label: String?, rescan: Boolean?) {
        delegate.importPrivateKey(privateKey, label, rescan)
    }

    override fun importPublicKey(publicKey: String, label: String?, rescan: Boolean?) {
        delegate.importPublicKey(publicKey, label, rescan)
    }

    override fun importWallet(walletFile: String) {
        delegate.importWallet(walletFile)
    }

    override fun keypoolRefill(newSize: Int) {
        delegate.keypoolRefill(newSize)
    }

    override fun listAddressGroupings(): List<*> {
        return delegate.listAddressGroupings()
    }

    override fun listBanned(): List<String> {
        return delegate.listBanned()
    }

    override fun listLockUnspent(): List<Map<*, *>> {
        return delegate.listLockUnspent()
    }

    override fun listReceivedByAddress(minConfirmations: Int?, includeEmpty: Boolean?, includeWatchOnly: Boolean?): List<Map<*, *>> {
        return delegate.listReceivedByAddress(minConfirmations, includeEmpty, includeWatchOnly)
    }

    override fun listSinceBlock(blockHash: String?, targetConfirmations: Int?, includeWatchOnly: Boolean?, includeRemoved: Boolean?): Map<*, *> {
        return delegate.listSinceBlock(blockHash, targetConfirmations, includeWatchOnly, includeRemoved)
    }

    override fun listTransactions(account: String?, count: Int?, skip: Int?, includeWatchOnly: Boolean?): List<Map<*, *>> {
        return delegate.listTransactions(account, count, skip, includeWatchOnly)
    }

    override fun listUnspent(minConfirmations: Int?, maxConfirmations: Int?, addresses: List<String>?, includeUnsafe: Boolean?, queryOptions: QueryOptions?): QueryResult {
        return delegate.listUnspent(minConfirmations, maxConfirmations, addresses, includeUnsafe, queryOptions)
    }

    override fun listWallets(): List<String> {
        return delegate.listWallets()
    }

    override fun lockUnspent(unlock: Boolean, unspentOutputs: List<OutPoint>): Boolean {
        return delegate.lockUnspent(unlock, unspentOutputs)
    }

    override fun ping() {
        delegate.ping()
    }

    override fun preciousBlock(block: String) {
        delegate.preciousBlock(block)
    }

    override fun prioritiseTransaction(transactionId: String, dummy: Int, feeDeltaSatoshis: Int) {
        delegate.prioritiseTransaction(transactionId, dummy, feeDeltaSatoshis)
    }

    override fun pruneBlockchain(blockHeightOrUnixTimestamp: Long) {
        delegate.pruneBlockchain(blockHeightOrUnixTimestamp)
    }

    override fun removePrunedFunds(transactionId: String) {
        delegate.removePrunedFunds(transactionId)
    }

    override fun sendMany(account: String, addressAmounts: Map<String, BigDecimal>, comment: String?, subtractFee: Boolean, replaceable: Boolean, minConfirmations: Int?, feeEstimateMode: FeeEstimateMode?) {
        delegate.sendMany(account, addressAmounts, comment, subtractFee, replaceable, minConfirmations, feeEstimateMode)
    }

    override fun sendRawTransaction(transaction: String) {
        delegate.sendRawTransaction(transaction)
    }

    override fun sendToAddress(address: String, amount: BigDecimal, comment: String?, commentTo: String?, subtractFee: Boolean?, replaceable: Boolean?, minConfirmations: Int?, feeEstimateMode: FeeEstimateMode?): String {
        return delegate.sendToAddress(address, amount, comment, commentTo, subtractFee, replaceable, minConfirmations, feeEstimateMode)
    }

    override fun setBan(address: String, operation: BitcoinRpcClient.NodeListOperation, seconds: Int) {
        delegate.setBan(address, operation, seconds)
    }

    override fun setTransactionFee(fee: Double) {
        delegate.setTransactionFee(fee)
    }

    override fun signMessage(address: String, message: String) {
        delegate.signMessage(address, message)
    }

    override fun signMessageWithPrivateKey(privateKey: String, message: String) {
        delegate.signMessageWithPrivateKey(privateKey, message)
    }

    override fun signRawTransaction(transactionId: String) {
        delegate.signRawTransaction(transactionId)
    }

    override fun submitBlock(blockData: String) {
        delegate.submitBlock(blockData)
    }

    override fun uptime(): Int {
        return delegate.uptime()
    }

    override fun validateAddress(address: String) {
        delegate.validateAddress(address)
    }

    override fun verifyChain() {
        delegate.verifyChain()
    }

    override fun verifyMessage(address: String, signature: String, message: String) {
        delegate.verifyMessage(address, signature, message)
    }

    override fun searchRawSerialisedTransactions(address: String, verbose: Int?, skip: Int?, count: Int?, vInExtra: Int?, reverse: Boolean?): List<String> {
        return delegate.searchRawSerialisedTransactions(address, verbose, skip, count, vInExtra, reverse)
    }

    override fun searchRawVerboseTransactions(address: String, verbose: Int?, skip: Int?, count: Int?, vInExtra: Int?, reverse: Boolean?): List<SearchedTransactionResult> {
        return delegate.searchRawVerboseTransactions(address, verbose, skip, count, vInExtra, reverse)
    }

}

interface WebSocketBitcoinRpcClient : BitcoinRpcClient {
    fun connect()
    fun disconnect()
}
