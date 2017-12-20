package com.github.jleskovar.btcrpc.websocket

import com.github.jleskovar.btcrpc.*
import java.math.BigDecimal
import java.util.concurrent.CompletableFuture

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

    override fun btcdAuthenticate(username: String, password: String): CompletableFuture<Void> {
        return delegate.btcdAuthenticate(username, password)
    }

    override fun btcdGenerate(numberOfBlocks: Int): CompletableFuture<List<String>> {
        return delegate.btcdGenerate(numberOfBlocks)
    }

    override fun btcdGetBlockWithTransactions(blockHash: String, verbose: Boolean): CompletableFuture<String> {
        return delegate.btcdGetBlockWithTransactions(blockHash, verbose)
    }

    override fun abandonTransaction(transactionId: String): CompletableFuture<Void> {
        return delegate.abandonTransaction(transactionId)
    }

    override fun abortRescan(): CompletableFuture<Void> {
        return delegate.abortRescan()
    }

    override fun addMultiSigAddress(required: Int?, keys: List<String>): CompletableFuture<String> {
        return delegate.addMultiSigAddress(required, keys)
    }

    override fun addNode(address: String, operation: NodeListOperation): CompletableFuture<Void> {
        return delegate.addNode(address, operation)
    }

    override fun backupWallet(destination: String): CompletableFuture<Void> {
        return delegate.backupWallet(destination)
    }

    override fun clearBanned(): CompletableFuture<Void> {
        return delegate.clearBanned()
    }

    override fun createMultiSig(required: Int, keys: List<String>): CompletableFuture<MultiSigAddress> {
        return delegate.createMultiSig(required, keys)
    }

    override fun createRawTransaction(inputs: List<OutPoint>, outputs: Map<String, BigDecimal>, lockTime: Int?, replaceable: Boolean?): CompletableFuture<String> {
        return delegate.createRawTransaction(inputs, outputs, lockTime, replaceable)
    }

    override fun decodeRawTransaction(transactionId: String): CompletableFuture<Transaction> {
        return delegate.decodeRawTransaction(transactionId)
    }

    override fun decodeScript(scriptHex: String): CompletableFuture<DecodedScript> {
        return delegate.decodeScript(scriptHex)
    }

    override fun disconnectNode(nodeAddress: String?, nodeId: Int?): CompletableFuture<Void> {
        return delegate.disconnectNode(nodeAddress, nodeId)
    }

    override fun dumpPrivateKey(address: String): CompletableFuture<String> {
        return delegate.dumpPrivateKey(address)
    }

    override fun dumpWallet(filename: String): CompletableFuture<Map<*, *>> {
        return delegate.dumpWallet(filename)
    }

    override fun encryptWallet(passphrase: String): CompletableFuture<Void> {
        return delegate.encryptWallet(passphrase)
    }

    override fun generate(numberOfBlocks: Int, maxTries: Int?): CompletableFuture<List<String>> {
        return delegate.generate(numberOfBlocks, maxTries)
    }

    override fun getAddedNodeInfo(): CompletableFuture<List<AddedNodeInfo>> {
        return delegate.getAddedNodeInfo()
    }

    override fun getBalance(account: String, minconf: Int, includeWatchOnly: Boolean): CompletableFuture<BigDecimal> {
        return delegate.getBalance(account, minconf, includeWatchOnly)
    }

    override fun getBestBlockhash(): CompletableFuture<String> {
        return delegate.getBestBlockhash()
    }

    override fun getBlockData(blockHash: String, verbosity: Int): CompletableFuture<String> {
        return delegate.getBlockData(blockHash, verbosity)
    }

    override fun getBlock(blockHash: String, verbosity: Int): CompletableFuture<BlockInfo> {
        return delegate.getBlock(blockHash, verbosity)
    }

    override fun getBlockWithTransactions(blockHash: String, verbosity: Int): CompletableFuture<BlockInfoWithTransactions> {
        return delegate.getBlockWithTransactions(blockHash, verbosity)
    }

    override fun getBlockchainInfo(): CompletableFuture<BlockChainInfo> {
        return delegate.getBlockchainInfo()
    }

    override fun getBlockCount(): CompletableFuture<Int> {
        return delegate.getBlockCount()
    }

    override fun getBlockHash(height: Int): CompletableFuture<String> {
        return delegate.getBlockHash(height)
    }

    override fun getBlockHeader(blockHash: String, verbose: Boolean?): CompletableFuture<Any> {
        return delegate.getBlockHeader(blockHash, verbose)
    }

    override fun getBlockTemplate(blockTemplateRequest: BlockTemplateRequest?): CompletableFuture<Void> {
        return delegate.getBlockTemplate(blockTemplateRequest)
    }

    override fun getChainTips(): CompletableFuture<List<ChainTip>> {
        return delegate.getChainTips()
    }

    override fun getChainTransactionStats(blockWindowSize: Int?, blockHashEnd: String?): CompletableFuture<ChainTransactionStats> {
        return delegate.getChainTransactionStats(blockWindowSize, blockHashEnd)
    }

    override fun getConnectionCount(): CompletableFuture<Int> {
        return delegate.getConnectionCount()
    }

    override fun getDifficulty(): CompletableFuture<BigDecimal> {
        return delegate.getDifficulty()
    }

    override fun getMemoryInfo(): CompletableFuture<Any> {
        return delegate.getMemoryInfo()
    }

    override fun getMempoolAncestors(transactionId: String): CompletableFuture<Any> {
        return delegate.getMempoolAncestors(transactionId)
    }

    override fun getMempoolDescendants(): CompletableFuture<Any> {
        return delegate.getMempoolDescendants()
    }

    override fun getMempoolEntry(transactionId: String): CompletableFuture<Map<*, *>> {
        return delegate.getMempoolEntry(transactionId)
    }

    override fun getMempoolInfo(): CompletableFuture<MemPoolInfo> {
        return delegate.getMempoolInfo()
    }

    override fun getMiningInfo(): CompletableFuture<MiningInfo> {
        return delegate.getMiningInfo()
    }

    override fun getNetworkTotals(): CompletableFuture<NetworkTotals> {
        return delegate.getNetworkTotals()
    }

    override fun getNetworkHashesPerSeconds(lastBlocks: Int, height: Int): CompletableFuture<Long> {
        return delegate.getNetworkHashesPerSeconds(lastBlocks, height)
    }

    override fun getNetworkInfo(): CompletableFuture<NetworkInfo> {
        return delegate.getNetworkInfo()
    }

    override fun getNewAddress(): CompletableFuture<String> {
        return delegate.getNewAddress()
    }

    override fun getPeerInfo(): CompletableFuture<List<PeerInfo>> {
        return delegate.getPeerInfo()
    }

    override fun getRawChangeAddress(): CompletableFuture<String> {
        return delegate.getRawChangeAddress()
    }

    override fun getRawMemPool(verbose: Boolean): CompletableFuture<List<Map<*, *>>> {
        return delegate.getRawMemPool(verbose)
    }

    override fun getRawTransaction(transactionId: String): CompletableFuture<Transaction> {
        return delegate.getRawTransaction(transactionId)
    }

    override fun getReceivedByAddress(address: String, minConfirmations: Int): CompletableFuture<BigDecimal> {
        return delegate.getReceivedByAddress(address, minConfirmations)
    }

    override fun getWalletTransaction(transactionId: String): CompletableFuture<Map<*, *>> {
        return delegate.getWalletTransaction(transactionId)
    }

    override fun getUnspentTransactionOutputInfo(transactionId: String, index: Int): CompletableFuture<Map<*, *>> {
        return delegate.getUnspentTransactionOutputInfo(transactionId, index)
    }

    override fun getUnspentTransactionOutputSetInfo(): CompletableFuture<UtxoSet> {
        return delegate.getUnspentTransactionOutputSetInfo()
    }

    override fun getWalletInfo(): CompletableFuture<Map<*, *>> {
        return delegate.getWalletInfo()
    }

    override fun importAddress(scriptOrAddress: String, label: String?, rescan: Boolean?, includePayToScriptHash: Boolean?): CompletableFuture<Void> {
        return delegate.importAddress(scriptOrAddress, label, rescan, includePayToScriptHash)
    }

    override fun importPrivateKey(privateKey: String, label: String?, rescan: Boolean?): CompletableFuture<Void> {
        return delegate.importPrivateKey(privateKey, label, rescan)
    }

    override fun importPublicKey(publicKey: String, label: String?, rescan: Boolean?): CompletableFuture<Void> {
        return delegate.importPublicKey(publicKey, label, rescan)
    }

    override fun importWallet(walletFile: String): CompletableFuture<Void> {
        return delegate.importWallet(walletFile)
    }

    override fun keypoolRefill(newSize: Int): CompletableFuture<Void> {
        return delegate.keypoolRefill(newSize)
    }

    override fun listAddressGroupings(): CompletableFuture<List<*>> {
        return delegate.listAddressGroupings()
    }

    override fun listBanned(): CompletableFuture<List<String>> {
        return delegate.listBanned()
    }

    override fun listLockUnspent(): CompletableFuture<List<Map<*, *>>> {
        return delegate.listLockUnspent()
    }

    override fun listReceivedByAddress(minConfirmations: Int?, includeEmpty: Boolean?, includeWatchOnly: Boolean?): CompletableFuture<List<Map<*, *>>> {
        return delegate.listReceivedByAddress(minConfirmations, includeEmpty, includeWatchOnly)
    }

    override fun listSinceBlock(blockHash: String?, targetConfirmations: Int?, includeWatchOnly: Boolean?, includeRemoved: Boolean?): CompletableFuture<Map<*, *>> {
        return delegate.listSinceBlock(blockHash, targetConfirmations, includeWatchOnly, includeRemoved)
    }

    override fun listTransactions(account: String?, count: Int?, skip: Int?, includeWatchOnly: Boolean?): CompletableFuture<List<Map<*, *>>> {
        return delegate.listTransactions(account, count, skip, includeWatchOnly)
    }

    override fun listUnspent(minConfirmations: Int?, maxConfirmations: Int?, addresses: List<String>?, includeUnsafe: Boolean?, queryOptions: QueryOptions?): CompletableFuture<QueryResult> {
        return delegate.listUnspent(minConfirmations, maxConfirmations, addresses, includeUnsafe, queryOptions)
    }

    override fun listWallets(): CompletableFuture<List<String>> {
        return delegate.listWallets()
    }

    override fun lockUnspent(unlock: Boolean, unspentOutputs: List<OutPoint>): CompletableFuture<Boolean> {
        return delegate.lockUnspent(unlock, unspentOutputs)
    }

    override fun ping(): CompletableFuture<Void> {
        return delegate.ping()
    }

    override fun preciousBlock(block: String): CompletableFuture<Void> {
        return delegate.preciousBlock(block)
    }

    override fun prioritiseTransaction(transactionId: String, dummy: Int, feeDeltaSatoshis: Int): CompletableFuture<Void> {
        return delegate.prioritiseTransaction(transactionId, dummy, feeDeltaSatoshis)
    }

    override fun pruneBlockchain(blockHeightOrUnixTimestamp: Long): CompletableFuture<Void> {
        return delegate.pruneBlockchain(blockHeightOrUnixTimestamp)
    }

    override fun removePrunedFunds(transactionId: String): CompletableFuture<Void> {
        return delegate.removePrunedFunds(transactionId)
    }

    override fun sendMany(account: String, addressAmounts: Map<String, BigDecimal>, comment: String?, subtractFee: Boolean, replaceable: Boolean, minConfirmations: Int?, feeEstimateMode: FeeEstimateMode?): CompletableFuture<Void> {
        return delegate.sendMany(account, addressAmounts, comment, subtractFee, replaceable, minConfirmations, feeEstimateMode)
    }

    override fun sendRawTransaction(transaction: String): CompletableFuture<Void> {
        return delegate.sendRawTransaction(transaction)
    }

    override fun sendToAddress(address: String, amount: BigDecimal, comment: String?, commentTo: String?, subtractFee: Boolean?, replaceable: Boolean?, minConfirmations: Int?, feeEstimateMode: FeeEstimateMode?): CompletableFuture<String> {
        return delegate.sendToAddress(address, amount, comment, commentTo, subtractFee, replaceable, minConfirmations, feeEstimateMode)
    }

    override fun setBan(address: String, operation: NodeListOperation, seconds: Int): CompletableFuture<Void> {
        return delegate.setBan(address, operation, seconds)
    }

    override fun setTransactionFee(fee: Double): CompletableFuture<Void> {
        return delegate.setTransactionFee(fee)
    }

    override fun signMessage(address: String, message: String): CompletableFuture<Void> {
        return delegate.signMessage(address, message)
    }

    override fun signMessageWithPrivateKey(privateKey: String, message: String): CompletableFuture<Void> {
        return delegate.signMessageWithPrivateKey(privateKey, message)
    }

    override fun signRawTransaction(transactionId: String): CompletableFuture<Void> {
        return delegate.signRawTransaction(transactionId)
    }

    override fun submitBlock(blockData: String): CompletableFuture<Void> {
        return delegate.submitBlock(blockData)
    }

    override fun uptime(): CompletableFuture<Int> {
        return delegate.uptime()
    }

    override fun validateAddress(address: String): CompletableFuture<Void> {
        return delegate.validateAddress(address)
    }

    override fun verifyChain(): CompletableFuture<Void> {
        return delegate.verifyChain()
    }

    override fun verifyMessage(address: String, signature: String, message: String): CompletableFuture<Void> {
        return delegate.verifyMessage(address, signature, message)
    }

    override fun searchRawSerialisedTransactions(address: String, verbose: Int?, skip: Int?, count: Int?, vInExtra: Int?, reverse: Boolean?): CompletableFuture<List<String>> {
        return delegate.searchRawSerialisedTransactions(address, verbose, skip, count, vInExtra, reverse)
    }

    override fun searchRawVerboseTransactions(address: String, verbose: Int?, skip: Int?, count: Int?, vInExtra: Int?, reverse: Boolean?): CompletableFuture<List<SearchedTransactionResult>> {
        return delegate.searchRawVerboseTransactions(address, verbose, skip, count, vInExtra, reverse)
    }

}
