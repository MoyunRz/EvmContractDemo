import cn.hutool.core.util.HexUtil;
import com.contract.proxy.Common1155Contract;
import com.contract.proxy.ContractsMetaTxForwarder;
import org.bitcoinj.crypto.DeterministicKey;
import org.junit.Test;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.Ethereum;
import org.web3j.protocol.core.methods.response.EthGetCode;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class ProxyContractTest {



    @Test
    public void GetContractBINARY() {
        ContractsMetaTxForwarder contractsMetaTxForwarder;
        if ((contractsMetaTxForwarder = MetaTxService.loadForwarderContract()) == null) {
            return;
        }
    }

    /**
     * myData
     * 进行数据测试
     * @return
     */
    public RequestTx myData() {


        RequestData requestData = new RequestData(
                "0x292F714cf36cD7d616425ec8c9D40187F60bf6Da",
                "0x175b6515de4abe508becf616b66cdc4438775075",
                BigInteger.valueOf(Long.parseLong("1610847933583450113")),
                BigInteger.valueOf(1),
                new byte[0]
        );

        BatchTxData batchTxData = new BatchTxData(
                "0x2cf8Eb3B0DbdA2104c556Fe70b6de3953A55d14f",
                "0x9F6D88DB7B7758A64faFAebb9f1cFF142eFfe07E",
                Arrays.asList(BigInteger.valueOf(1)),
                Arrays.asList(BigInteger.valueOf(1)),
                new byte[0]
        );

        RequestTx requestTx = new RequestTx();
        requestTx.setFrom(ChainConfig.SENDER_ADDRESS);
        requestTx.setContractAddress(ChainConfig.CONTRACT_ADDRESS);
        requestTx.setValue(BigInteger.valueOf(0));
        requestTx.setGas(BigInteger.valueOf(75842));
        requestTx.setNonce(BigInteger.valueOf(0));
        requestTx.setData("0xf242432a000000000000000000000000292f714cf36cd7d616425ec8c9d40187f60bf6da000000000000000000000000175b6515de4abe508becf616b66cdc4438775075000000000000000000000000000000000000000000000000165ae1a93469e001000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000a00000000000000000000000000000000000000000000000000000000000000000");
        requestTx.setRequestData(requestData);
        requestTx.setBatchTxData(batchTxData);

        return requestTx;
    }

    /**
     * TestTxVerify
     * 用户一对一 发送单个token 签名转账校验
     * a->b转账 tokenId:1 数量: 1000
     */
    @Test
    public void TestSimpleTxVerify1() throws ExecutionException, InterruptedException {

        ContractsMetaTxForwarder.ForwardRequest req = new ContractsMetaTxForwarder.ForwardRequest(
                new Address(160, "0x292F714cf36cD7d616425ec8c9D40187F60bf6Da"),
                new Address(160, "0x7a1771B9Aea7eCea009db701f044a11De5535601"),
                new Uint256(0),
                new Uint256(75842),
                new Uint256(0),
                new DynamicBytes(HexUtil.decodeHex("f242432a000000000000000000000000292f714cf36cd7d616425ec8c9d40187f60bf6da000000000000000000000000175b6515de4abe508becf616b66cdc4438775075000000000000000000000000000000000000000000000000165ae1a93469e001000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000a00000000000000000000000000000000000000000000000000000000000000000"))
        );

        ContractsMetaTxForwarder contractsMetaTxForwarder;
        if ((contractsMetaTxForwarder = MetaTxService.loadForwarderContract()) == null) {
            return;
        }
        String hexSignatureStr = "0x9386c41bbd22f883df8802ed59b9bb3ec102fbb80801b29ac8a3674d2ba974407738292752eece064dc38274aec71225872f361e177b09afb03b9c90699011511b";
        byte[] signature = HexUtil.decodeHex(hexSignatureStr.replaceFirst("0x", ""));
        // 开始调用
        Boolean v = contractsMetaTxForwarder.verify(req, signature).sendAsync().get();
        if (v) {
            System.out.println("success");
        } else {
            System.out.println("error");
        }
    }
    /**
     * TestTxVerify
     * 用户一对一 发送单个token 签名转账校验
     * a->b转账 tokenId:1 数量: 1000
     */
    @Test
    public void TestSimpleTxVerify() {

        // TODO 前端传进来的 data 数据里的额外数据
        // 原始数据
        RequestTx requestTx = myData();
        // 签名后的数据
        String hexSignatureStr = "0x9386c41bbd22f883df8802ed59b9bb3ec102fbb80801b29ac8a3674d2ba974407738292752eece064dc38274aec71225872f361e177b09afb03b9c90699011511b";
        // =================

        ContractsMetaTxForwarder contractsMetaTxForwarder;
        if ((contractsMetaTxForwarder = MetaTxService.loadForwarderContract()) == null) {
            return;
        }
        try {
            // 测试时获取nonce,平时需要前端传
            BigInteger nonce = contractsMetaTxForwarder.getNonce(requestTx.from).sendAsync().get();

            // 前端传签名字符 要记得去掉0x，转换为byte[]
            byte[] signature = HexUtil.decodeHex(hexSignatureStr.replaceFirst("0x", ""));
            // 在合约里是递增的

            // 前端传调用真实际合约的函数 要记得去掉0x，转换为byte[]
            byte[] remoteCallData = HexUtil.decodeHex(requestTx.data.replaceFirst("0x", ""));

            // TODO 在这可以进行后端对data里原文的 from和to地址、金额、tokenId校验：
//            if (!requestTx.from.equals(requestTx.requestData.from)) {
//                System.out.println("签名地址和发送地址不一样");
//                return;
//            }

            // end --------------------

            // 校验data 数据是否被篡改
            Function function = new Function(
                    Common1155Contract.FUNC_SAFETRANSFERFROM,
                    Arrays.<Type>asList(new Address(160, requestTx.requestData.from),
                            new Address(160, requestTx.requestData.to),
                            new Uint256(requestTx.requestData.tokenId),
                            new Uint256(requestTx.requestData.amount),
                            new DynamicBytes(requestTx.requestData.externalData)),
                    Collections.<TypeReference<?>>emptyList());

            String encodedFunction = FunctionEncoder.encode(function);

            // 判断前端的交易数据是否正确
            if (encodedFunction.equals(requestTx.data)) {

                ContractsMetaTxForwarder.ForwardRequest req = new ContractsMetaTxForwarder.ForwardRequest(
                        new Address(160, "0x292F714cf36cD7d616425ec8c9D40187F60bf6Da"),
                        new Address(160, "0x7a1771B9Aea7eCea009db701f044a11De5535601"),
                        new Uint256(0),
                        new Uint256(75842),
                        new Uint256(0),
                        new DynamicBytes(remoteCallData)
                );
                // 开始调用
                Boolean v = contractsMetaTxForwarder.verify(req, signature).sendAsync().get();
                if (v) {
                    System.out.println("success");
                } else {
                    System.out.println("error");
                }
            }


        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * TestTxBatchVerify
     * 用户一对一 发送多个token  签名转账校验
     * a->b转账 tokenId:[0,1] 数量:[10,50]
     */
    @Test
    public void TestTxBatchVerify() {

        // TODO 前端传进来的 data 数据里的额外数据
        // 原始数据
        RequestTx requestTx = myData();
        // 签名后的数据
        String hexSignatureStr = "0xb365dc90bb71c85c531fad7ef18f3c2c30131a2d54088dcc7bc93613c889122d24053fb233400cfdc1ceb2a59adaedc0e2e005b710e12e55fb987a6767e999a01c";
        // =================

        // 初始化的测试数据，前端传


        ContractsMetaTxForwarder contractsMetaTxForwarder;
        if ((contractsMetaTxForwarder = MetaTxService.loadForwarderContract()) == null) {
            return;
        }
        try {
            // 测试时获取nonce,平时需要前端传
            BigInteger nonce = contractsMetaTxForwarder.getNonce(requestTx.from).sendAsync().get();
            // 前端传签名字符 要记得去掉0x，转换为byte[]
            byte[] signature = HexUtil.decodeHex(hexSignatureStr.replaceFirst("0x", ""));
            // 在合约里是递增的
            // 前端传调用真实际合约的函数 要记得去掉0x，转换为byte[]
            byte[] remoteCallData = HexUtil.decodeHex(requestTx.data.replaceFirst("0x", ""));


            // TODO 在这可以进行后端对data里原文的 from和to地址、金额、tokenId校验：
            if (!requestTx.from.equals(requestTx.batchTxData.from)) {
                System.out.println("签名地址和发送地址不一样");
                return;
            }

            // end --------------------



            // TODO 校验data 数据是否被篡改
            Function function = new Function(
                    Common1155Contract.FUNC_SAFEBATCHTRANSFERFROM,
                    Arrays.<Type>asList(new Address(160, requestTx.batchTxData.from),
                            new Address(160, requestTx.batchTxData.getTo()),
                            new DynamicArray<Uint256>(
                                    Uint256.class,
                                    org.web3j.abi.Utils.typeMap(requestTx.batchTxData.tokenIds, Uint256.class)),
                            new DynamicArray<Uint256>(
                                    Uint256.class,
                                    org.web3j.abi.Utils.typeMap(requestTx.batchTxData.amounts, Uint256.class)),
                            new DynamicBytes(requestTx.batchTxData.externalData)),
                    Collections.<TypeReference<?>>emptyList());

            String encodedFunction = FunctionEncoder.encode(function);

            // 判断前端的data交易数据是否正确
            if (encodedFunction.equals(requestTx.getData())) {
                // 构建交易请求
                ContractsMetaTxForwarder.ForwardRequest req = new ContractsMetaTxForwarder.ForwardRequest(
                        new Address(160, requestTx.from),
                        new Address(160, requestTx.contractAddress),
                        new Uint256(0),
                        new Uint256(requestTx.gas),
                        new Uint256(nonce),
                        new DynamicBytes(remoteCallData)
                );

                // 开始调用
                Boolean v = contractsMetaTxForwarder.verify(req, signature).sendAsync().get();
                if (v) {
                    System.out.println("success");
                } else {
                    System.out.println("error");
                }
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 交易广播执行
     */
    @Test
    public void TestExecute() {

        // TODO 前端传进来的 data 数据里的额外数据
        // 原始数据
        RequestTx requestTx = myData();
        // 签名后的数据
        String hexSignatureStr = "0xb365dc90bb71c85c531fad7ef18f3c2c30131a2d54088dcc7bc93613c889122d24053fb233400cfdc1ceb2a59adaedc0e2e005b710e12e55fb987a6767e999a01c";
        // =================

        ContractsMetaTxForwarder contractsMetaTxForwarder;
        if ((contractsMetaTxForwarder = MetaTxService.loadForwarderContract()) == null) {
            return;
        }
        try {

            // 测试时获取nonce,平时需要前端传
            BigInteger nonce = contractsMetaTxForwarder.getNonce(requestTx.from).sendAsync().get();
            // 前端传签名字符 要记得去掉0x，转换为byte[]
            byte[] signature = HexUtil.decodeHex(hexSignatureStr.replaceFirst("0x", ""));
            // 在合约里是递增的
            // 前端传调用真实际合约的函数 要记得去掉0x，转换为byte[]
            byte[] remoteCallData = HexUtil.decodeHex(requestTx.data.replaceFirst("0x", ""));

            // 前端传调用真实际合约的函数 要记得去掉0x，转换为byte[]
            ContractsMetaTxForwarder.ForwardRequest req = new ContractsMetaTxForwarder.ForwardRequest(
                    new Address(160, requestTx.from),
                    new Address(160, requestTx.contractAddress),
                    new Uint256(0),
                    new Uint256(requestTx.gas),
                    new Uint256(nonce),
                    new DynamicBytes(remoteCallData)
            );

            // 开始调用
            TransactionReceipt v = contractsMetaTxForwarder.execute(req, signature, req.gas).sendAsync().get();
            System.out.println(v.getTransactionHash());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * TestSendNftByProxyExecute
     * 平台用户走代理合约进行转账给外部 NFT
     *
     *
     */
    @Test
    public void TestSendNftByProxyExecute() {

        // TODO 前端传进来的 data 数据里的额外数据
        // 原始数据
        RequestTx requestTx = myData();
        // 签名后的数据
        String hexSignatureStr = "0xb365dc90bb71c85c531fad7ef18f3c2c30131a2d54088dcc7bc93613c889122d24053fb233400cfdc1ceb2a59adaedc0e2e005b710e12e55fb987a6767e999a01c";
        // =================


        // 构建转账函数
        Function function = new org.web3j.abi.datatypes.Function(
                Common1155Contract.FUNC_SAFETRANSFERFROM,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, requestTx.requestData.from),
                        new org.web3j.abi.datatypes.Address(160, requestTx.requestData.to),
                        new org.web3j.abi.datatypes.generated.Uint256(requestTx.requestData.tokenId),
                        new org.web3j.abi.datatypes.generated.Uint256(requestTx.requestData.amount),
                        new org.web3j.abi.datatypes.DynamicBytes(requestTx.requestData.externalData)),
                Collections.<TypeReference<?>>emptyList());
        // 进行函数编码
        String encodedFunction = FunctionEncoder.encode(function);
        // 进行签名
    }
}
