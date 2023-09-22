
import com.contract.proxy.ContractsMetaTxForwarder;
import com.contract.trace.ContractsTraceSource;
import org.junit.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthGetCode;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class TraceSourceTest {


    @Test
    public void GetContractBINARY() {
        ContractsTraceSource contractsMetaTxForwarder;
        if ((contractsMetaTxForwarder = TraceStoreService.loadContractsTraceSource()) == null) {
            return;
        }
    }

    /**
     * TestStoreSource
     * 进行合约存储和查询测试
     */
    @Test
    public void TestStoreSource() {
        String dataJson = "{\n" +
                "        \"\\u66f4\\u65b0hash\": \"bba5be284d0e6f39c91944cb646dae4a\",\n" +
                "        \"\\u4f1a\\u5458id\": \"141771\",\n" +
                "        \"\\u4f1a\\u5458\\u4fe1\\u606f\": \"hash:1586953570857521153\",\n" +
                "        \"\\u5f53\\u524d\\u72b6\\u6001\": \"\\u5df2\\u6536\\u8d27\",\n" +
                "        \"\\u8ba2\\u5355\\u7f16\\u53f7\": \"1640017770636791808-400\",\n" +
                "        \"\\u4e0b\\u5355\\u65f6\\u95f4\": \"2023-03-26 23:48:01\",\n" +
                "        \"\\u4ed8\\u6b3e\\u65f6\\u95f4\": \"2023-03-26 23:48:23\",\n" +
                "        \"\\u6536\\u8d27\\u4eba\": \"\\u5434**\",\n" +
                "        \"\\u5546\\u54c1\\u91d1\\u989d\": \"32.09\",\n" +
                "        \"\\u8ba2\\u5355\\u91d1\\u989d\": \"32.09\",\n" +
                "        \"\\u5730\\u5740\": \"\\u6c5f\\u82cf\\u5e38\\u5dde\\u5e02\\u5929\\u5b81\\u533a**1\\u5e62401\",\n" +
                "        \"\\u7535\\u8bdd\": \"**3870\",\n" +
                "        \"\\u3010\\u8106\\u5ae9\\u723d\\u53e3\\uff0c\\u73b0\\u6458\\u73b0\\u53d1\\u3011\\u5c71\\u4e1c\\u5bff\\u5149\\u6c34\\u679c\\u9ec4\\u74dc \\u5c0f\\u9752\\u74dc \\u751f\\u5403\\u5373\\u98df2\\u65a4\\/3\\u65a4\\/5\\u65a4_3\\u65a4\\u88c5--\\u6c34\\u679c\\u9ec4\\u74dc\\/\\u7bb1\\u88c5_1\": 1\n" +
                "    }";

        ContractsTraceSource storeSource = TraceStoreService.LoadContracts();
        try {
            long nowTime = System.currentTimeMillis();
            // 进行存储
            TransactionReceipt v = storeSource.StoreSource(dataJson).sendAsync().get();
            System.out.println(System.currentTimeMillis() - nowTime);

            List<ContractsTraceSource.StoreLogEventResponse> result = storeSource.getStoreLogEvents(v);
            String onlyCode = Utils.bytesToHexString(result.get(0).param0);
            // 存储后的key值
            System.out.println("存储后的key值:0x" + onlyCode);
            // 进行查询
            String res = storeSource.FindSource(Utils.hexStringToByteArray(onlyCode)).sendAsync().get();
            System.out.println("查询结果:" + res);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * TestStoreSource
     * 进行合约存储和查询测试
     */
    @Test
    public void TestBatchStore() {
        List<String> dataJson = new ArrayList<>();

        dataJson.add("{'收货人':'巫1','电话':'**0009','会员id':'116936'}");
        dataJson.add("{'收货人':'巫22','电话':'**0009','会员id':'116937'}");
        dataJson.add("{'收货人':'巫23','电话':'**0009','会员id':'116938'}");
        dataJson.add("{'收货人':'巫24','电话':'**0009','会员id':'116939'}");
        dataJson.add("{'收货人':'巫25','电话':'**0009','会员id':'116940'}");
        dataJson.add("{'收货人':'巫26','电话':'**0009','会员id':'116951'}");
        dataJson.add("{'收货人':'巫2','电话':'**0009','会员id':'116952'}");
        dataJson.add("{'收货人':'巫3','电话':'**0009','会员id':'116953'}");
        dataJson.add("{'收货人':'巫4','电话':'**0009','会员id':'116954'}");
        dataJson.add("{'收货人':'巫5','电话':'**0009','会员id':'116955'}");
        dataJson.add("{'收货人':'巫6','电话':'**0009','会员id':'116956'}");
        dataJson.add("{'收货人':'巫7','电话':'**0009','会员id':'116957'}");
        dataJson.add("{'收货人':'巫8','电话':'**0009','会员id':'116958'}");
        dataJson.add("{'收货人':'巫9','电话':'**0009','会员id':'116959'}");

        ContractsTraceSource storeSource = TraceStoreService.LoadContracts();
        try {
            long nowTime = System.currentTimeMillis();
            // 进行存储
            TransactionReceipt v = storeSource.BatchStore(dataJson).sendAsync().get();
            System.out.println(System.currentTimeMillis() - nowTime);

            List<ContractsTraceSource.StoreLogEventResponse> result = storeSource.getStoreLogEvents(v);

            for (ContractsTraceSource.StoreLogEventResponse r : result) {
                String onlyCode = Utils.bytesToHexString(r.param0);
                // 存储后的key值
                System.out.println("存储后的key值:0x" + onlyCode);
                // 进行查询
                String res = storeSource.FindSource(Utils.hexStringToByteArray(onlyCode)).sendAsync().get();
                System.out.println("查询结果:" + res);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * TestTxVerify
     * 用户一对一 发送单个token 签名转账校验
     * a->b转账 tokenId:1 数量: 1000
     */
    @Test
    public void TestFindStoreSource() {
        ContractsTraceSource storeSource = TraceStoreService.LoadContracts();
        try {
            // 进行查询
            String res = storeSource.FindSource(Utils.hexStringToByteArray("3287E6385C635CB0E292F3E9752198A767356049F2E618295FC3EFE1F1C07753")).sendAsync().get();
            System.out.println("查询结果:" + res);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
