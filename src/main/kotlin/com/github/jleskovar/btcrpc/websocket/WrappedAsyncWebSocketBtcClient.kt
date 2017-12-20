package com.github.jleskovar.btcrpc.websocket

import com.github.jleskovar.btcrpc.*
import java.math.BigDecimal
import java.util.concurrent.Future

class WrappedAsyncWebSocketBtcClient(
        private val username: String,
        private val password: String,
        private val delegate: AsyncBitcoinRpcClient,
        private val jsonWebSocketRpcClient: JsonAsyncWebSocketRpcClient
) : AsyncWebSocketBitcoinRpcClient {

    override fun connect() {
        jsonWebSocketRpcClient.connect()
        // Authenticate as soon as web socket is open (btcd)
        delegate.btcdAuthenticate(username, password)
    }

    override fun disconnect() {
        jsonWebSocketRpcClient.disconnect()
    }

    override fun btcdAuthenticate(username: String, password: String) {
        delegate.btcdAuthenticate(username, password)
    }

    override fun btcdGenerate(numberOfBlocks: Int): Future<List<String>> {
        return delegate.btcdGenerate(numberOfBlocks)
    }

    override fun btcdGetBlockWithTransactions(blockHash: String, verbose: Boolean): Future<String> {
        return delegate.btcdGetBlockWithTransactions(blockHash, verbose)
    }

    override fun abandonTransaction(transactionId: String) {
        delegate.abandonTransaction(transactionId)
    }

    override fun abortRescan() {
        delegate.abortRescan()
    }

    override fun addMultiSigAddress(required: Int?, keys: List<String>): Future<String> {
        return delegate.addMultiSigAddress(required, keys)
    }

    override fun addNode(address: String, operation: NodeListOperation) {
        delegate.addNode(address, operation)
    }

    override fun backupWallet(destination: String) {
        delegate.backupWallet(destination)
    }

    override fun clearBanned() {
        delegate.clearBanned()
    }

    override fun createMultiSig(required: Int, keys: List<String>): Future<MultiSigAddress> {
        return delegate.createMultiSig(required, keys)
    }

    override fun createRawTransaction(inputs: List<OutPoint>, outputs: Map<String, BigDecimal>, lockTime: Int?, replaceable: Boolean?): Future<String> {
        return delegate.createRawTransaction(inputs, outputs, lockTime, replaceable)
    }

    override fun decodeRawTransaction(transactionId: String): Future<Transaction> {
        return delegate.decodeRawTransaction(transactionId)
    }

    override fun decodeScript(scriptHex: String): Future<DecodedScript> {
        return delegate.decodeScript(scriptHex)
    }

    override fun disconnectNode(nodeAddress: String?, nodeId: Int?) {
        delegate.disconnectNode(nodeAddress, nodeId)
    }

    override fun dumpPrivateKey(address: String): Future<String> {
        return delegate.dumpPrivateKey(address)
    }

    override fun dumpWallet(filename: String): Future<Map<*, *>> {
        return delegate.dumpWallet(filename)
    }

    override fun encryptWallet(passphrase: String) {
        delegate.encryptWallet(passphrase)
    }

    override fun generate(numberOfBlocks: Int, maxTries: Int?): Future<List<String>> {
        return delegate.generate(numberOfBlocks, maxTries)
    }

    override fun getAddedNodeInfo(): Future<List<AddedNodeInfo>> {
        return delegate.getAddedNodeInfo()
    }

    override fun getBalance(account: String, minconf: Int, includeWatchOnly: Boolean): Future<BigDecimal> {
        return delegate.getBalance(account, minconf, includeWatchOnly)
    }

    override fun getBestBlockhash(): Future<String> {
        return delegate.getBestBlockhash()
    }

    override fun getBlockData(blockHash: String, verbosity: Int): Future<String> {
        return delegate.getBlockData(blockHash, verbosity)
    }

    override fun getBlock(blockHash: String, verbosity: Int): Future<BlockInfo> {
        return delegate.getBlock(blockHash, verbosity)
    }

    override fun getBlockWithTransactions(blockHash: String, verbosity: Int): Future<BlockInfoWithTransactions> {
        return delegate.getBlockWithTransactions(blockHash, verbosity)
    }

    override fun getBlockchainInfo(): Future<BlockChainInfo> {
        return delegate.getBlockchainInfo()
    }

    override fun getBlockCount(): Future<Int> {
        return delegate.getBlockCount()
    }

    override fun getBlockHash(height: Int): Future<String> {
        return delegate.getBlockHash(height)
    }

    override fun getBlockHeader(blockHash: String, verbose: Boolean?): Future<Any> {
        return delegate.getBlockHeader(blockHash, verbose)
    }

    override fun getBlockTemplate(blockTemplateRequest: BlockTemplateRequest?) {
        delegate.getBlockTemplate(blockTemplateRequest)
    }

    override fun getChainTips(): Future<List<ChainTip>> {
        return delegate.getChainTips()
    }

    override fun getChainTransactionStats(blockWindowSize: Int?, blockHashEnd: String?): Future<ChainTransactionStats> {
        return delegate.getChainTransactionStats(blockWindowSize, blockHashEnd)
    }

    override fun getConnectionCount(): Future<Int> {
        return delegate.getConnectionCount()
    }

    override fun getDifficulty(): Future<BigDecimal> {
        return delegate.getDifficulty()
    }

    override fun getMemoryInfo(): Future<Any> {
        return delegate.getMemoryInfo()
    }

    override fun getMempoolAncestors(transactionId: String): Future<Any> {
        return delegate.getMempoolAncestors(transactionId)
    }

    override fun getMempoolDescendants(): Future<Any> {
        return delegate.getMempoolDescendants()
    }

    override fun getMempoolEntry(transactionId: String): Future<Map<*, *>> {
        return delegate.getMempoolEntry(transactionId)
    }

    override fun getMempoolInfo(): Future<MemPoolInfo> {
        return delegate.getMempoolInfo()
    }

    override fun getMiningInfo(): Future<MiningInfo> {
        return delegate.getMiningInfo()
    }

    override fun getNetworkTotals(): Future<NetworkTotals> {
        return delegate.getNetworkTotals()
    }

    override fun getNetworkHashesPerSeconds(lastBlocks: Int, height: Int): Future<Long> {
        return delegate.getNetworkHashesPerSeconds(lastBlocks, height)
    }

    override fun getNetworkInfo(): Future<NetworkInfo> {
        return delegate.getNetworkInfo()
    }

    override fun getNewAddress(): Future<String> {
        return delegate.getNewAddress()
    }

    override fun getPeerInfo(): Future<List<PeerInfo>> {
        return delegate.getPeerInfo()
    }

    override fun getRawChangeAddress(): Future<String> {
        return delegate.getRawChangeAddress()
    }

    override fun getRawMemPool(verbose: Boolean): Future<List<Map<*, *>>> {
        return delegate.getRawMemPool(verbose)
    }

    override fun getRawTransaction(transactionId: String): Future<Transaction> {
        return delegate.getRawTransaction(transactionId)
    }

    override fun getReceivedByAddress(address: String, minConfirmations: Int): Future<BigDecimal> {
        return delegate.getReceivedByAddress(address, minConfirmations)
    }

    override fun getWalletTransaction(transactionId: String): Future<Map<*, *>> {
        return delegate.getWalletTransaction(transactionId)
    }

    override fun getUnspentTransactionOutputInfo(transactionId: String, index: Int): Future<Map<*, *>> {
        return delegate.getUnspentTransactionOutputInfo(transactionId, index)
    }

    override fun getUnspentTransactionOutputSetInfo(): Future<UtxoSet> {
        return delegate.getUnspentTransactionOutputSetInfo()
    }

    override fun getWalletInfo(): Future<Map<*, *>> {
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

    override fun listAddressGroupings(): Future<List<*>> {
        return delegate.listAddressGroupings()
    }

    override fun listBanned(): Future<List<String>> {
        return delegate.listBanned()
    }

    override fun listLockUnspent(): Future<List<Map<*, *>>> {
        return delegate.listLockUnspent()
    }

    override fun listReceivedByAddress(minConfirmations: Int?, includeEmpty: Boolean?, includeWatchOnly: Boolean?): Future<List<Map<*, *>>> {
        return delegate.listReceivedByAddress(minConfirmations, includeEmpty, includeWatchOnly)
    }

    override fun listSinceBlock(blockHash: String?, targetConfirmations: Int?, includeWatchOnly: Boolean?, includeRemoved: Boolean?): Future<Map<*, *>> {
        return delegate.listSinceBlock(blockHash, targetConfirmations, includeWatchOnly, includeRemoved)
    }

    override fun listTransactions(account: String?, count: Int?, skip: Int?, includeWatchOnly: Boolean?): Future<List<Map<*, *>>> {
        return delegate.listTransactions(account, count, skip, includeWatchOnly)
    }

    override fun listUnspent(minConfirmations: Int?, maxConfirmations: Int?, addresses: List<String>?, includeUnsafe: Boolean?, queryOptions: QueryOptions?): Future<QueryResult> {
        return delegate.listUnspent(minConfirmations, maxConfirmations, addresses, includeUnsafe, queryOptions)
    }

    override fun listWallets(): Future<List<String>> {
        return delegate.listWallets()
    }

    override fun lockUnspent(unlock: Boolean, unspentOutputs: List<OutPoint>): Future<Boolean> {
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

    override fun sendToAddress(address: String, amount: BigDecimal, comment: String?, commentTo: String?, subtractFee: Boolean?, replaceable: Boolean?, minConfirmations: Int?, feeEstimateMode: FeeEstimateMode?): Future<String> {
        return delegate.sendToAddress(address, amount, comment, commentTo, subtractFee, replaceable, minConfirmations, feeEstimateMode)
    }

    override fun setBan(address: String, operation: NodeListOperation, seconds: Int) {
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

    override fun uptime(): Future<Int> {
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

    override fun searchRawSerialisedTransactions(address: String, verbose: Int?, skip: Int?, count: Int?, vInExtra: Int?, reverse: Boolean?): Future<List<String>> {
        return delegate.searchRawSerialisedTransactions(address, verbose, skip, count, vInExtra, reverse)
    }

    override fun searchRawVerboseTransactions(address: String, verbose: Int?, skip: Int?, count: Int?, vInExtra: Int?, reverse: Boolean?): Future<List<SearchedTransactionResult>> {
        return delegate.searchRawVerboseTransactions(address, verbose, skip, count, vInExtra, reverse)
    }

}
