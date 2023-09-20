import com.contract.proxy.ContractsMetaTxForwarder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthGetCode;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class MetaTxService {

    public static ContractsMetaTxForwarder loadForwarderContract() {
        try {
            EthGetCode ethGetCode = ChainConfig.WEB3J.ethGetCode(ChainConfig.PROXY_CONTRACT_ADDRESS, ChainConfig.CHAIN_VERSION).sendAsync().get();
            ContractsMetaTxForwarder.BINARY = String.valueOf(ethGetCode);

            ContractsMetaTxForwarder contractsMetaTxForwarder = ContractsMetaTxForwarder.load(
                    ChainConfig.PROXY_CONTRACT_ADDRESS, ChainConfig.WEB3J, ChainConfig.BRIDGE_MANAGER, new DefaultGasProvider());
            if (!contractsMetaTxForwarder.isValid()) {
                System.out.println("加载Proxy合约不是有效的");
                return null;
            }
            return contractsMetaTxForwarder;
        } catch (IOException e) {
            System.out.println("验证Proxy合约io流有问题");
            return null;
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
