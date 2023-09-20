import com.contract.proxy.ContractsMetaTxForwarder;
import com.contract.trace.ContractsTraceSource;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthGetCode;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class TraceStoreService {
    public static ContractsTraceSource loadContractsTraceSource() {
        Web3j web3j = ChainConfig.WEB3J;
        TransactionManager bridgeTokenTxManager = new RawTransactionManager(
                ChainConfig.WEB3J,
                ChainConfig.CREDENTIALS,
                ChainConfig.CHAIN_ID,
                15, 1000
        );
        try {
            EthGetCode ethGetCode = web3j.ethGetCode(ChainConfig.TRACE_SOURCE_CONTRACT_ADDRESS, DefaultBlockParameter.valueOf("latest")).sendAsync().get();
            ContractsMetaTxForwarder.BINARY = String.valueOf(ethGetCode);

            ContractsTraceSource contractsTraceSource = ContractsTraceSource.load(
                    ChainConfig.TRACE_SOURCE_CONTRACT_ADDRESS, web3j, bridgeTokenTxManager, new DefaultGasProvider());

            if (!contractsTraceSource.isValid()) {
                System.out.println("加载TraceSource合约不是有效的");
                return null;
            }
            return contractsTraceSource;
        } catch (IOException e) {
            System.out.println("验证TraceSource合约io流有问题");
            return null;
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
