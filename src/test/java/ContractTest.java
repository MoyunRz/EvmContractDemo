import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import com.alibaba.fastjson.JSONObject;

import com.contract.proxy.Common1155Contract;
import net.evm.abi.decoder.AbiDecoder;
import net.evm.abi.decoder.DecodedFunctionCall;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;
import org.web3j.utils.Strings;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class ContractTest {


    @Test
    public void testHex() {
        String str = "我是一个字符串";
        String hex = HexUtil.encodeHexStr(str, CharsetUtil.CHARSET_UTF_8);
        System.out.println(HexUtil.format("0x" + hex));
        // hex是：
        // e68891e698afe4b880e4b8aae5ad97e7aca6e4b8b2
        String decodedStr = HexUtil.decodeHexStr(hex);

        System.out.println(decodedStr);

        JSONObject object = new JSONObject();
        //string
        object.put("string", "string");
        //int
        object.put("int", 2);
        //boolean
        object.put("boolean", true);
        //array
        List<Integer> integers = Arrays.asList(1, 2, 3);
        object.put("list", integers);

        byte[] s = Utils.getMessageHash(object.toJSONString());
        System.out.println(Arrays.toString(s));
        System.out.println(object.toJSONString());

        // 备注的额外信息可以传金额，也可以不传
        String data = HexUtil.encodeHexStr(object.toJSONString());
        System.out.println(Arrays.toString(HexUtil.decodeHex(data)));
        System.out.println(HexUtil.decodeHexStr(data));

    }

    /**
     * testMintNFT
     * 测试 1155 Mint 铸造
     */
    @Test
    public void testMintNFT() {
        Common1155Contract common1155Contract;
        if ((common1155Contract = ERCService.loadCommon1155Contract()) == null) {
            return;
        }

        List<String> tos = new ArrayList<>();           // 发送给谁
        List<BigInteger> ids = new ArrayList<>();       // tokenId
        List<BigInteger> amounts = new ArrayList<>();   // 发送数量

        // 类型 0 单个一对一
        // 类型 1 from -> to 多个token
        // 类型 2 批量mint a->b,a->c,a->d;
        // 转账 tokenId: [1,2,3], 数量: [10,20,50]
        // a->b (1:10),
        // a->c (2:20),
        // a->d (3:50)
        BigInteger txType = BigInteger.valueOf(0);


        JSONObject object = new JSONObject();
        //string
        object.put("string", "string");
        //int
        object.put("int", 2);
        //boolean
        object.put("boolean", true);
        //array
        List<Integer> integers = Arrays.asList(1, 2, 3);
        object.put("list", integers);
        // 备注的额外信息可以传金额，也可以不传
        String hex = HexUtil.encodeHexStr(object.toJSONString());
        byte[] data = HexUtil.decodeHex(hex);

        tos.add("0x2812d96d61c1f689caedaba8bdf349972d955c32");
        ids.add(BigInteger.valueOf(Long.parseLong("1604045668965015553")));
        amounts.add(BigInteger.valueOf(100));

        try {
            TransactionReceipt mintReceipt = common1155Contract.exchangeMint(tos, ids, amounts, txType, data).sendAsync().get();
            System.out.println(mintReceipt.getTransactionHash());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * testBalanceOf
     * 测试余额获取
     */
    @Test
    public void testGetBalanceByPlateAccount() {
        try {
            Common1155Contract common1155Contract;
            if ((common1155Contract = ERCService.loadCommon1155Contract()) == null) {
                return;
            }
            BigInteger balance = common1155Contract.balanceOf(ChainConfig.SENDER_ADDRESS, BigInteger.valueOf(0)).send();
            System.out.println(balance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * testTxNFT
     * 测试 1155 Mint 转账测试
     */
    @Test
    public void testTxNFT() {
        Common1155Contract common1155Contract;
        if ((common1155Contract = ERCService.loadCommon1155Contract()) == null) {
            return;
        }

        List<String> tos = new ArrayList<>();           // 发送给谁
        List<BigInteger> ids = new ArrayList<>();       // tokenId
        List<BigInteger> amounts = new ArrayList<>();   // 发送数量

        // 类型 0 单个一对一
        // 类型 1 from -> to 多个token
        // 类型 2 批量mint a->b,a->c,a->d;
        // 转账 tokenId: [1,2,3], 数量: [10,20,50]
        // a->b (1:10),
        // a->c (2:20),
        // a->d (3:50)
        BigInteger txType = BigInteger.valueOf(0);


        JSONObject object = new JSONObject();
        //string
        object.put("string", "string");
        //int
        object.put("int", 2);
        //boolean
        object.put("boolean", true);
        //array
        List<Integer> integers = Arrays.asList(1, 2, 3);
        object.put("list", integers);

        // 备注的额外信息可以传金额，也可以不传
        String hex = HexUtil.encodeHexStr(object.toJSONString());
        byte[] data = HexUtil.decodeHex(hex);

//        tos.add(ChainConfig.TO_ADDRESS);
        tos.add("0xc5E889999785312c2452c6D5f482582adedE048b");

        ids.add(BigInteger.valueOf(Long.parseLong("1604045668965015553")));
//        ids.add(BigInteger.valueOf(2));
//        amounts.add(BigInteger.valueOf(1000));
        amounts.add(BigInteger.valueOf(2));

        try {
            TransactionReceipt mintReceipt = common1155Contract.exchangeTransfer("0x2812d96d61c1f689caedaba8bdf349972d955c32", tos, ids, amounts, txType, data).sendAsync().get();
            System.out.println(mintReceipt.getTransactionHash());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }


    /**
     * testWithoutTxNFT
     * 测试 1155 Mint 外部转账
     * 备注：手续费由发送者出，就是form地址出，from地址和调用者地址要一样
     */
    @Test
    public void testTxNFTWithout() {
        Common1155Contract common1155Contract;
        if ((common1155Contract = ERCService.loadCommon1155Contract()) == null) {
            return;
        }

        List<String> tos = new ArrayList<>();           // 发送给谁
        List<BigInteger> ids = new ArrayList<>();       // tokenId
        List<BigInteger> amounts = new ArrayList<>();   // 发送数量

        JSONObject object = new JSONObject();
        //string
        object.put("string", "string");
        //int
        object.put("int", 2);
        //boolean
        object.put("boolean", true);
        //array
        List<Integer> integers = Arrays.asList(1, 2, 3);
        object.put("list", integers);
        // 备注的额外信息可以传金额，也可以不传
        String hex = HexUtil.encodeHexStr(object.toJSONString());
        byte[] data = HexUtil.decodeHex(hex);

        tos.add(ChainConfig.TO_ADDRESS);
        ids.add(BigInteger.valueOf(1));
        amounts.add(BigInteger.valueOf(1000));
        long tokenId = Long.parseLong("1684045668965015553");
        try {
            // String from 发送者地址
            // String to 接收者地址
            // BigInteger id 发送的Token
            // BigInteger amount 发送的数量
            // byte[] data 格外的数据
            TransactionReceipt txReceipt = common1155Contract.safeTransferFrom(
                    ChainConfig.SENDER_ADDRESS,
                    ChainConfig.TO_ADDRESS,
                    BigInteger.valueOf(tokenId),
                    BigInteger.valueOf(10),
                    data
            ).sendAsync().get();

            System.out.println(txReceipt.getTransactionHash());

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * TestEthSendTransaction
     * 发起一笔交易（自定义参数）
     * eth转账和合约的erc转账区别是 to地址不同:
     * <p>
     * eth转账的to是用户地址;
     * 合约的erc转账是转给合约地址,然后调用合约内部的方法
     */
    @Test
    public void TestEthSendTransaction() {
        String from = ChainConfig.SENDER_ADDRESS;
        String to = ChainConfig.TO_ADDRESS;
        String privateKey = ChainConfig.PRIVATE_KEY;
        BigInteger value = BigInteger.valueOf(1000000000);

        try {
            BigInteger gasPrice = ChainConfig.getGasPrice();
            BigInteger gasLimit = BigInteger.valueOf(2100000);
            //加载转账所需的凭证，用私钥
            Credentials credentials = Credentials.create(privateKey);

            BigInteger nonce = ERCService.getNonceByFrom(from);

            //创建RawTransaction交易对象
            RawTransaction rawTransaction = RawTransaction.createEtherTransaction(nonce, gasPrice, gasLimit, to, value);
            //签名Transaction，这里要对交易做签名
            byte[] signMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
            String hexValue = Numeric.toHexString(signMessage);

            //发送交易
            EthSendTransaction ethSendTransaction = ChainConfig.WEB3J.ethSendRawTransaction(hexValue).sendAsync().get();
            System.out.println(ethSendTransaction.getTransactionHash());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发起代币(NFT/ERC20)转账
     */
    @Test
    public void TestSendToken() {


        try {
            Credentials credentials = Credentials.create(ChainConfig.PRIVATE_KEY);

            String fromAddress = credentials.getAddress();

            EthGetTransactionCount ethGetTransactionCount = null;
            ethGetTransactionCount = ChainConfig.WEB3J.ethGetTransactionCount(
                    fromAddress, DefaultBlockParameterName.LATEST).sendAsync().get();
            BigInteger nonce = ethGetTransactionCount.getTransactionCount();

            String hex = HexUtil.encodeHexStr("我是转账的备注");
            byte[] data = HexUtil.decodeHex(hex);

            Function function = new Function(
                    "safeTransferFrom",
                    Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, ChainConfig.SENDER_ADDRESS),
                            new org.web3j.abi.datatypes.Address(160, ChainConfig.TO_ADDRESS),
                            new org.web3j.abi.datatypes.generated.Uint256(0),
                            new org.web3j.abi.datatypes.generated.Uint256(100),
                            new org.web3j.abi.datatypes.DynamicBytes(data)),
                    Collections.<TypeReference<?>>emptyList());

            String encodedFunction = FunctionEncoder.encode(function);

            RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, Convert.toWei("0.1", Convert.Unit.GWEI).toBigInteger(),
                    BigInteger.valueOf(300000L), ChainConfig.CONTRACT_ADDRESS, encodedFunction);

            byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
            String hexValue = Numeric.toHexString(signedMessage);
            EthSendTransaction ethSendTransaction = ChainConfig.WEB3J.ethSendRawTransaction(hexValue).sendAsync().get();

            String transactionHash = ethSendTransaction.getTransactionHash();
            System.out.println(transactionHash);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    /**
     * testGetTxInfoByHash
     * 测试通过Hash获取交易信息
     * 用 evm-abi-decoder 解析input交易信息
     */
    @Test
    public void testGetTxInfoByHash() {
        try {
            AbiDecoder abiJson = new AbiDecoder(this.getClass().getResource("/abiFiles/XunWenGe.json").getFile());

            // github上的解析demo地址： https://github.com/rvullriede/evm-abi-decoder.git
            EthTransaction ethTransaction = ChainConfig.WEB3J.ethGetTransactionByHash("0x8990e045f7d52e799a3b0e0edbf1b64ac33160b2f8a6929c8d30841bf9f69aed").sendAsync().get();
            String inputData = ethTransaction.getResult().getInput();

            DecodedFunctionCall decodedFunctionCall = abiJson.decodeFunctionCall(inputData);

            Assertions.assertEquals("exchangeMint", decodedFunctionCall.getName());

            List<DecodedFunctionCall.Param> paramList = decodedFunctionCall.getParamList();

            DecodedFunctionCall.Param param0 = paramList.get(0);

            Assertions.assertEquals("tos", param0.getName());
            Assertions.assertEquals("address[]", param0.getType());
            System.out.println(param0.getValue());

            DecodedFunctionCall.Param param1 = paramList.get(1);
            Assertions.assertEquals("ids", param1.getName());
            Assertions.assertEquals("uint256[]", param1.getType());
            System.out.println(param1.getValue());

            DecodedFunctionCall.Param param2 = paramList.get(2);
            Assertions.assertEquals("amounts", param2.getName());
            Assertions.assertEquals("uint256[]", param2.getType());
            Object[] list2 = (Object[]) param2.getValue();
            System.out.println(list2[0]);

            DecodedFunctionCall.Param param3 = paramList.get(3);
            Assertions.assertEquals("txType", param3.getName());
            Assertions.assertEquals("uint8", param3.getType());
            System.out.println(param3.getValue());

            DecodedFunctionCall.Param param4 = paramList.get(4);
            // 打印
            Assertions.assertEquals("data", param4.getName());
            Assertions.assertEquals("bytes", param4.getType());
            System.out.println(param4.getValue());
            String s = HexUtil.decodeHexStr(param4.getValue().toString().replaceFirst("0x", ""));
            System.out.println(s);

        } catch (InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * testGetTxReceiptByHash
     * 测试通过Hash获取交易回执信息
     */
    @Test
    public void testGetTxReceiptByHash() {
        try {
            EthGetTransactionReceipt ethGetTransactionReceipt = ChainConfig.WEB3J.ethGetTransactionReceipt("0x7704a042c5289f6687c89530f1a251c3acec2e51ca179395808ab6a26802282a").sendAsync().get();
            String hex = ethGetTransactionReceipt.getResult().getStatus();
            // 判断状态是否成功
            if (!Strings.isEmpty(hex)) {
                String status = hex.substring(2).equals("1") ? "成功" : "失败";
                System.out.println(status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * testGetTxReceiptByHash
     * 测试通过Hash获取交易回执信息
     */
    @Test
    public void testTxInfo() {
        String inputData = "0xa9059cbb0000000000000000000000008e7e315fd8965b0fadc7c404307a55d5a6ccf15500000000000000000000000000000000000000000000000012f5dc3926dbd000";
        String method = inputData.substring(0, 10);
        System.out.println(method);
        String to = inputData.substring(10, 74);
        String value = inputData.substring(74);
        Method refMethod;
        try {
            refMethod = TypeDecoder.class.getDeclaredMethod("decode", String.class, int.class, Class.class);
            refMethod.setAccessible(true);
            Address address = (Address) refMethod.invoke(null, to, 0, Address.class);
            System.out.println(address.toString());
            Uint256 amount = (Uint256) refMethod.invoke(null, value, 0, Uint256.class);
            System.out.println(amount.getValue());
        } catch (NoSuchMethodException | InvocationTargetException | IllegalArgumentException | IllegalAccessException | SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * testGetTxReceiptByHash
     * 测试通过Hash获取交易回执信息
     */
    @Test
    public void testSetUri() {
        Common1155Contract common1155Contract;
        if ((common1155Contract = ERCService.loadCommon1155Contract()) == null) {
            return;
        }
        try {
            TransactionReceipt transactionReceipt = common1155Contract.setURI("").sendAsync().get();
            System.out.println(transactionReceipt.getTransactionHash());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
