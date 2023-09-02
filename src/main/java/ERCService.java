import com.contract.proxy.Common1155Contract;
import com.contract.proxy.ContractsMetaTxForwarder;
import com.contract.trace.ContractsTraceSource;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;


/**
 * @Author jambestwick
 * @create 2021/12/5 0005  21:04
 * @email jambestwick@126.com
 * <p>
 * ERC1155的代币
 */
public class ERCService {

    public static Common1155Contract loadCommon1155Contract() {
        Credentials credentials = ChainConfig.CREDENTIALS;
        TransactionManager bridgeTokenTxManager = new RawTransactionManager(
                ChainConfig.WEB3J,
                credentials,
                ChainConfig.CHAIN_ID
        );
        ContractGasProvider contractGasProvider = new DefaultGasProvider();
        Common1155Contract common1155Contract = Common1155Contract.load(ChainConfig.CONTRACT_ADDRESS, ChainConfig.WEB3J, bridgeTokenTxManager, contractGasProvider);
        try {
            if (!common1155Contract.isValid()) {
                System.out.println("加载1155合约不是有效的");
                return null;
            }
        } catch (IOException e) {
            System.out.println("验证1155合约io流有问题");
            return null;
        }
        return common1155Contract;
    }
    public static BigInteger getBalanceByPlateAccount(String account,BigInteger tokenId) {
        try {
            Common1155Contract common1155Contract;
            if ((common1155Contract = loadCommon1155Contract()) == null) {
                return BigInteger.ZERO;
            }
            BigInteger balance = common1155Contract.balanceOf(account, tokenId).send();
          return balance;
        } catch (Exception e) {
            e.printStackTrace();
            return BigInteger.ZERO;
        }
    }

    public static BigInteger getNonceByFrom(String from) {
        try{
        //获取nonce，交易笔数
        EthGetTransactionCount transactionCount = ChainConfig.WEB3J.ethGetTransactionCount(from, DefaultBlockParameterName.LATEST).sendAsync().get();
        return transactionCount.getTransactionCount();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return BigInteger.ZERO;
        }
    }

}


