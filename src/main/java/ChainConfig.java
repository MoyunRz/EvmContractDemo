
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Async;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

public class ChainConfig {

    // 发送者地址
    static String SENDER_ADDRESS = "0xB58656873901b66bdfB3C46B8b2Ff2286159ebbc";
    // 发送者私钥
    static String PRIVATE_KEY = "e853d16d26634761ddcd1e13b5978a5115aa3c198137f3150b7af686b2b9ee28";
    // 接收者地址
    static String TO_ADDRESS = "0x29255f2a67Ec9b1356De88457323a52388c4B3AD";
    // 代理合约地址
    static String PROXY_CONTRACT_ADDRESS = "0x79d9ebc2E6863aCFdedD03E4fe3Dbae1A76744B7";
    // 1155合约地址
    static String CONTRACT_ADDRESS = "0x5d4232D8D4AB5bd25Ec594eAeAC507EebcEAE4b5";
    // 溯源存储合约地址
    static String TRACE_SOURCE_CONTRACT_ADDRESS = "0xFad2e66c49A193f3ffDed7e59931D3B0F8089cB5";

    // 华为云服务器 链的RPC地址 chain id = 9090
     static Web3j WEB3J = Web3j.build(new HttpService("http://121.41.66.213:9933"));

    // 加载私钥
    static Credentials CREDENTIALS = Credentials.create(ChainConfig.PRIVATE_KEY);
    // 链ID
    static long CHAIN_ID = 90;
    // 获取Gas 也就是手续费
    static public BigInteger getGasPrice() throws ExecutionException, InterruptedException {
        return ChainConfig.WEB3J.ethGasPrice().sendAsync().get().getGasPrice();
    }
}
