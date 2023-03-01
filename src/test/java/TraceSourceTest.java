
import com.contract.trace.ContractsTraceSource;
import org.junit.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class TraceSourceTest {

    public static ContractsTraceSource loadContractsTraceSource() {
        Web3j web3j = ChainConfig.WEB3J;
        TransactionManager bridgeTokenTxManager = new RawTransactionManager(
                ChainConfig.WEB3J,
                ChainConfig.CREDENTIALS,
                ChainConfig.CHAIN_ID
        );
        ContractsTraceSource contractsTraceSource = ContractsTraceSource.load(
                ChainConfig.TRACE_SOURCE_CONTRACT_ADDRESS, web3j, bridgeTokenTxManager, new DefaultGasProvider());
        try {
            if (!contractsTraceSource.isValid()) {
                System.out.println("加载TraceSource合约不是有效的");
                return null;
            }
        } catch (IOException e) {
            System.out.println("验证TraceSource合约io流有问题");
            return null;
        }
        return contractsTraceSource;
    }


    /**
     * TestTxVerify
     * 用户一对一 发送单个token 签名转账校验
     * a->b转账 tokenId:1 数量: 1000
     */
    @Test
    public void TestStoreSource() {
        ContractsTraceSource contractsStoreSource;
        if ((contractsStoreSource = loadContractsTraceSource()) == null) {
            return;
        }
        try {
            // 进行存储
            TransactionReceipt v = contractsStoreSource.StoreSource("{'orderSn':'20230226000512186','outOrderNo':'20230226000512186','orderSellerName':'名牌商品汇','orderTypeName':'共信鼎','thirdId':'1629512832550830080-5','jdOrderNum':'260227672795','customerName':'共信鼎','consignee':'夏黎','mobilePhoneNumber':'13454472782','inTheArea':'浙江 杭州市 余杭区','detailedAddress':'余杭街道永建洪桐吴岐山12号','totalAmount':null,'payJiuLingAmount':18.16,'payZeroCoupon':0.00,'totalNum':1,'logisticsCost':10.00,'createTime':null,'orderTime':'2023-02-25 16:05:11','goodsCourier':null,'ecmJDOrderGoodDtos':[{'serNum':1,'kidOrderNumber':null,'barCode':'','name':'百草味混合装冻干水果30g/袋 芒果干草莓脆休闲办公室零食草莓干黄桃干','format':'颜色:【冻干水果】6种混合口味30g型号:默认','goodsNum':1,'purchasingPrice':8.0,'retailPrice':8.16,'returnNum':0,'amountPercentage':8.16}]}").sendAsync().get();
            List<ContractsTraceSource.StoreLogEventResponse> result = contractsStoreSource.getStoreLogEvents(v);
            String onlyCode = Utils.bytesToHexString(result.get(0).param0);

            // 存储后的key值
            System.out.println("存储后的key值:" + onlyCode);
            // 进行查询
            String res = contractsStoreSource.FindSource(Utils.hexStringToByteArray(onlyCode)).sendAsync().get();
            System.out.println("查询结果:" + res);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
