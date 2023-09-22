
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Async;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

public class ChainConfig {

    // 发送者地址
    static String SENDER_ADDRESS = "0xA433963e078609811c228e3a8AA85e60B87F532d";
    // 发送者私钥
    static String PRIVATE_KEY = "aa24ba816a8d8025c9c4af1c15aa1e2f69f86238441fea189d9da0cf85b8f40b";
    // 接收者地址
    static String TO_ADDRESS = "0x29255f2a67Ec9b1356De88457323a52388c4B3AD";
    // 代理合约地址
    static String PROXY_CONTRACT_ADDRESS = "0x79d9ebc2E6863aCFdedD03E4fe3Dbae1A76744B7";
    // 1155合约地址
    static String CONTRACT_ADDRESS = "0x5d4232D8D4AB5bd25Ec594eAeAC507EebcEAE4b5";
    // 溯源存储合约地址
    static String TRACE_SOURCE_CONTRACT_ADDRESS = "0x4AB443a69281BA11a441F90c7b416889B82E6A48";

    // 华为云服务器 链的RPC地址 chain id = 9090
     static Web3j WEB3J = Web3j.build(new HttpService("http://119.23.220.135:9933"));

    // 加载私钥
    static Credentials CREDENTIALS = Credentials.create(ChainConfig.PRIVATE_KEY);
    // 链ID
    static long CHAIN_ID = 90;
    static DefaultBlockParameter CHAIN_VERSION = DefaultBlockParameter.valueOf("latest");

    // 获取Gas 也就是手续费
    static public BigInteger getGasPrice() throws ExecutionException, InterruptedException {
        return ChainConfig.WEB3J.ethGasPrice().sendAsync().get().getGasPrice();
    }

    static TransactionManager BRIDGE_MANAGER = new RawTransactionManager(
            ChainConfig.WEB3J,
            ChainConfig.CREDENTIALS,
            ChainConfig.CHAIN_ID
    );
}
