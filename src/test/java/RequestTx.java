import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class RequestTx {
    // 签名者
    public String from;
    // 合约地址
    public String contractAddress;
    // 转账给合约的eth，这里默认填0
    public BigInteger value;
    // 费用
    public BigInteger gas;
    // 获取改地址的请求数
    public BigInteger nonce;
    // 前端hex后的请求数据
    public String data;
    // 单个tokenId 转账的原始数据
    public RequestData requestData;
    // 多个tokenId 转账的原始数据
    public BatchTxData batchTxData;


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }

    public BigInteger getGas() {
        return gas;
    }

    public void setGas(BigInteger gas) {
        this.gas = gas;
    }

    public BigInteger getNonce() {
        return nonce;
    }

    public void setNonce(BigInteger nonce) {
        this.nonce = nonce;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public RequestData getRequestData() {
        return requestData;
    }

    public void setRequestData(RequestData requestData) {
        this.requestData = requestData;
    }

    public BatchTxData getBatchTxData() {
        return batchTxData;
    }

    public void setBatchTxData(BatchTxData batchTxData) {
        this.batchTxData = batchTxData;
    }
}

/**
 * 单个tokenId 转账的请求数据
 */
class RequestData {
    // tokenId 发送者
    public String from;
    // tokenId 接收者
    public String to;
    // tokenId
    public BigInteger tokenId;
    // 发送数量
    public BigInteger amount;
    // 调用转账函数时，想要记录在链上的额外数据，没有就填 new byte[0]
    public byte[] externalData;

    public RequestData(String from, String to, BigInteger tokenId, BigInteger amount, byte[] externalData) {
        this.from = from;
        this.to = to;
        this.tokenId = tokenId;
        this.amount = amount;
        this.externalData = externalData;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigInteger getTokenId() {
        return tokenId;
    }

    public void setTokenId(BigInteger tokenId) {
        this.tokenId = tokenId;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    public byte[] getExternalData() {
        return externalData;
    }

    public void setExternalData(byte[] externalData) {
        this.externalData = externalData;
    }

    @Override
    public String toString() {
        return "RequestTx{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", tokenId=" + tokenId +
                ", amount=" + amount +
                ", externalData=" + Arrays.toString(externalData) +
                '}';
    }
}

class BatchTxData {
    // 代币（token）发送者
    public String from;
    // 代币（token）接收者
    public String to;
    // 转账多个TokenId 时 指定那几个token
    public List<BigInteger> tokenIds;
    // 转账多个TokenId 时 每个对应的金额数量
    public List<BigInteger> amounts;
    // 调用转账函数时，想要记录在链上的额外数据，没有就填 new byte[0]
    public byte[] externalData;

    public BatchTxData(String from, String to, List<BigInteger> tokenIds, List<BigInteger> amounts, byte[] externalData) {
        this.from = from;
        this.to = to;
        this.tokenIds = tokenIds;
        this.amounts = amounts;
        this.externalData = externalData;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<BigInteger> getTokenIds() {
        return tokenIds;
    }

    public void setTokenId(List<BigInteger> tokenIds) {
        this.tokenIds = tokenIds;
    }

    public List<BigInteger> getAmount() {
        return amounts;
    }

    public void setAmount(List<BigInteger> amounts) {
        this.amounts = amounts;
    }

    public byte[] getExternalData() {
        return externalData;
    }

    public void setExternalData(byte[] externalData) {
        this.externalData = externalData;
    }
}
