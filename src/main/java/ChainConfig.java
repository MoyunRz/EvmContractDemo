
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Async;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

public class ChainConfig {

    // 发送者地址
    static String SENDER_ADDRESS = "";
    // 发送者私钥
    static String PRIVATE_KEY = "";
    // 接收者地址
    static String TO_ADDRESS = "0x29255f2a67Ec9b1356De88457323a52388c4B3AD";
    // 代理合约地址
    static String PROXY_CONTRACT_ADDRESS = "";
    // 1155合约地址
    static String CONTRACT_ADDRESS = "";
    // 溯源存储合约地址
    static String TRACE_SOURCE_CONTRACT_ADDRESS = "";

    // 华为云服务器 链的RPC地址 chain id = 9090
     static Web3j WEB3J = Web3j.build(new HttpService(""));

    // 加载私钥
    static Credentials CREDENTIALS = Credentials.create(ChainConfig.PRIVATE_KEY);
    // 链ID
    static long CHAIN_ID = 90;
    // 获取Gas 也就是手续费
    static public BigInteger getGasPrice() throws ExecutionException, InterruptedException {
        return ChainConfig.WEB3J.ethGasPrice().sendAsync().get().getGasPrice();
    }
}
