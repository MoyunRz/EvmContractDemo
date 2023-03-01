
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

public class ChainConfig {
    // 发送者地址
    static String SENDER_ADDRESS = "0x2cf8Eb3B0DbdA2104c556Fe70b6de3953A55d14f";
    // 发送者私钥
    static String PRIVATE_KEY = "a34b3571633428696859962adf0ca17345714de93c8f2cb2f5a8ab35e8d22d98";
    // 接收者地址
    static String TO_ADDRESS = "0x29255f2a67Ec9b1356De88457323a52388c4B3AD";
    // 1155合约地址
    static String CONTRACT_ADDRESS = "0x6b421fAA48FEcd9EE604a63AA2D55f14e4B3Eb54";

    // 代理合约地址
    static String PROXY_CONTRACT_ADDRESS = "0x6619B44A2e5Cf39eE39E179feE5d281488B718c8";

    // 溯源存储合约地址
    static String TRACE_SOURCE_CONTRACT_ADDRESS = "0x94c9428a77623766F4664Fed4B5500dfC7776214";

    // 华为云服务器 链的RPC地址 chain id = 9090
     static Web3j WEB3J = Web3j.build(new HttpService("http://124.71.12.16:9933"));

    // 腾讯云 链的RPC地址 chain id = 42
    // static Web3j WEB3J = Web3j.build(new HttpService("http://129.204.192.194:7033"));
    // 本地 链的RPC地址 chain id = 9090
    // static Web3j WEB3J = Web3j.build(new HttpService("http://127.0.0.1:9933"));

    // 加载私钥
    static Credentials CREDENTIALS = Credentials.create(ChainConfig.PRIVATE_KEY);
    // 链ID
    static long CHAIN_ID = 9090;
    // 获取Gas 也就是手续费
    static public BigInteger getGasPrice() throws ExecutionException, InterruptedException {
        return ChainConfig.WEB3J.ethGasPrice().sendAsync().get().getGasPrice();
    }
}
