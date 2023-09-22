package com.contract.trace;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.2.
 */
@SuppressWarnings("rawtypes")
public class ContractsTraceSource extends Contract {
    public static final String BINARY = "60806040525f600455348015610013575f80fd5b50335f806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610efb806100605f395ff3fe608060405234801561000f575f80fd5b506004361061004a575f3560e01c8063420d65ab1461004e578063b346cfb11461006a578063b7ddb98c1461009a578063d7465fd8146100b6575b5f80fd5b6100686004803603810190610063919061070c565b6100e6565b005b610084600480360381019061007f91906107ad565b61028e565b60405161009191906107f2565b60405180910390f35b6100b460048036038101906100af91906108ed565b6103c2565b005b6100d060048036038101906100cb9190610967565b6104e5565b6040516100dd9190610a0c565b60405180910390f35b60035f3373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020015f205f9054906101000a900460ff168061018557503373ffffffffffffffffffffffffffffffffffffffff165f8054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16145b6101c4576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016101bb90610a76565b60405180910390fd5b5f600454826040516020016101da929190610af7565b6040516020818303038152906040528051906020012090508160015f8381526020019081526020015f2090816102109190610d18565b50600160025f8381526020019081526020015f205f6101000a81548160ff0219169083151502179055507f4665aef0905d7d6f5b12ed7500efd9957ba62f72d0a02445df1131e374c06d69818360405161026b929190610df6565b60405180910390a160045f81548092919061028590610e51565b91905055505050565b5f3373ffffffffffffffffffffffffffffffffffffffff165f8054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161461031c576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161031390610a76565b60405180910390fd5b600160035f8473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020015f205f6101000a81548160ff02191690831515021790555060035f8373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020015f205f9054906101000a900460ff169050919050565b60035f3373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020015f205f9054906101000a900460ff168061046157503373ffffffffffffffffffffffffffffffffffffffff165f8054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16145b6104a0576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161049790610a76565b60405180910390fd5b5f5b81518110156104e1576104ce8282815181106104c1576104c0610e98565b5b60200260200101516100e6565b80806104d990610e51565b9150506104a2565b5050565b606060025f8381526020019081526020015f205f9054906101000a900460ff1661051f5760405180602001604052805f81525090506105ba565b60015f8381526020019081526020015f20805461053b90610b4b565b80601f016020809104026020016040519081016040528092919081815260200182805461056790610b4b565b80156105b25780601f10610589576101008083540402835291602001916105b2565b820191905f5260205f20905b81548152906001019060200180831161059557829003601f168201915b505050505090505b919050565b5f604051905090565b5f80fd5b5f80fd5b5f80fd5b5f80fd5b5f601f19601f8301169050919050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52604160045260245ffd5b61061e826105d8565b810181811067ffffffffffffffff8211171561063d5761063c6105e8565b5b80604052505050565b5f61064f6105bf565b905061065b8282610615565b919050565b5f67ffffffffffffffff82111561067a576106796105e8565b5b610683826105d8565b9050602081019050919050565b828183375f83830152505050565b5f6106b06106ab84610660565b610646565b9050828152602081018484840111156106cc576106cb6105d4565b5b6106d7848285610690565b509392505050565b5f82601f8301126106f3576106f26105d0565b5b813561070384826020860161069e565b91505092915050565b5f60208284031215610721576107206105c8565b5b5f82013567ffffffffffffffff81111561073e5761073d6105cc565b5b61074a848285016106df565b91505092915050565b5f73ffffffffffffffffffffffffffffffffffffffff82169050919050565b5f61077c82610753565b9050919050565b61078c81610772565b8114610796575f80fd5b50565b5f813590506107a781610783565b92915050565b5f602082840312156107c2576107c16105c8565b5b5f6107cf84828501610799565b91505092915050565b5f8115159050919050565b6107ec816107d8565b82525050565b5f6020820190506108055f8301846107e3565b92915050565b5f67ffffffffffffffff821115610825576108246105e8565b5b602082029050602081019050919050565b5f80fd5b5f61084c6108478461080b565b610646565b9050808382526020820190506020840283018581111561086f5761086e610836565b5b835b818110156108b657803567ffffffffffffffff811115610894576108936105d0565b5b8086016108a189826106df565b85526020850194505050602081019050610871565b5050509392505050565b5f82601f8301126108d4576108d36105d0565b5b81356108e484826020860161083a565b91505092915050565b5f60208284031215610902576109016105c8565b5b5f82013567ffffffffffffffff81111561091f5761091e6105cc565b5b61092b848285016108c0565b91505092915050565b5f819050919050565b61094681610934565b8114610950575f80fd5b50565b5f813590506109618161093d565b92915050565b5f6020828403121561097c5761097b6105c8565b5b5f61098984828501610953565b91505092915050565b5f81519050919050565b5f82825260208201905092915050565b5f5b838110156109c95780820151818401526020810190506109ae565b5f8484015250505050565b5f6109de82610992565b6109e8818561099c565b93506109f88185602086016109ac565b610a01816105d8565b840191505092915050565b5f6020820190508181035f830152610a2481846109d4565b905092915050565b7f596f7520617265207065726d697373696f6e2064656e696564000000000000005f82015250565b5f610a6060198361099c565b9150610a6b82610a2c565b602082019050919050565b5f6020820190508181035f830152610a8d81610a54565b9050919050565b5f819050919050565b5f819050919050565b610ab7610ab282610a94565b610a9d565b82525050565b5f81905092915050565b5f610ad182610992565b610adb8185610abd565b9350610aeb8185602086016109ac565b80840191505092915050565b5f610b028285610aa6565b602082019150610b128284610ac7565b91508190509392505050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52602260045260245ffd5b5f6002820490506001821680610b6257607f821691505b602082108103610b7557610b74610b1e565b5b50919050565b5f819050815f5260205f209050919050565b5f6020601f8301049050919050565b5f82821b905092915050565b5f60088302610bd77fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82610b9c565b610be18683610b9c565b95508019841693508086168417925050509392505050565b5f819050919050565b5f610c1c610c17610c1284610a94565b610bf9565b610a94565b9050919050565b5f819050919050565b610c3583610c02565b610c49610c4182610c23565b848454610ba8565b825550505050565b5f90565b610c5d610c51565b610c68818484610c2c565b505050565b5b81811015610c8b57610c805f82610c55565b600181019050610c6e565b5050565b601f821115610cd057610ca181610b7b565b610caa84610b8d565b81016020851015610cb9578190505b610ccd610cc585610b8d565b830182610c6d565b50505b505050565b5f82821c905092915050565b5f610cf05f1984600802610cd5565b1980831691505092915050565b5f610d088383610ce1565b9150826002028217905092915050565b610d2182610992565b67ffffffffffffffff811115610d3a57610d396105e8565b5b610d448254610b4b565b610d4f828285610c8f565b5f60209050601f831160018114610d80575f8415610d6e578287015190505b610d788582610cfd565b865550610ddf565b601f198416610d8e86610b7b565b5f5b82811015610db557848901518255600182019150602085019450602081019050610d90565b86831015610dd25784890151610dce601f891682610ce1565b8355505b6001600288020188555050505b505050505050565b610df081610934565b82525050565b5f604082019050610e095f830185610de7565b8181036020830152610e1b81846109d4565b90509392505050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52601160045260245ffd5b5f610e5b82610a94565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8203610e8d57610e8c610e24565b5b600182019050919050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52603260045260245ffdfea2646970667358221220366783927f31f090a3f4a8979a8b006ddb5c9b405fd1ad8b52986bdb71e6183e64736f6c63430008150033";

    public static final String FUNC_BATCHSTORE = "BatchStore";

    public static final String FUNC_FINDSOURCE = "FindSource";

    public static final String FUNC_SETWHITE = "SetWhite";

    public static final String FUNC_STORESOURCE = "StoreSource";

    public static final Event STORELOG_EVENT = new Event("StoreLog",
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected ContractsTraceSource(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ContractsTraceSource(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ContractsTraceSource(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ContractsTraceSource(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<StoreLogEventResponse> getStoreLogEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(STORELOG_EVENT, transactionReceipt);
        ArrayList<StoreLogEventResponse> responses = new ArrayList<StoreLogEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            StoreLogEventResponse typedResponse = new StoreLogEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.param0 = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.param1 = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<StoreLogEventResponse> storeLogEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, StoreLogEventResponse>() {
            @Override
            public StoreLogEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(STORELOG_EVENT, log);
                StoreLogEventResponse typedResponse = new StoreLogEventResponse();
                typedResponse.log = log;
                typedResponse.param0 = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.param1 = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<StoreLogEventResponse> storeLogEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(STORELOG_EVENT));
        return storeLogEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> BatchStore(List<String> dataJson) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BATCHSTORE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Utf8String>(
                        org.web3j.abi.datatypes.Utf8String.class,
                        org.web3j.abi.Utils.typeMap(dataJson, org.web3j.abi.datatypes.Utf8String.class))),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> FindSource(byte[] hashId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_FINDSOURCE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(hashId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> SetWhite(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETWHITE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> StoreSource(String dataJson) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_STORESOURCE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(dataJson)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static ContractsTraceSource load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ContractsTraceSource(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ContractsTraceSource load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ContractsTraceSource(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ContractsTraceSource load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ContractsTraceSource(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ContractsTraceSource load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ContractsTraceSource(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ContractsTraceSource> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ContractsTraceSource.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<ContractsTraceSource> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ContractsTraceSource.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ContractsTraceSource> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ContractsTraceSource.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ContractsTraceSource> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ContractsTraceSource.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class StoreLogEventResponse extends BaseEventResponse {
        public byte[] param0;

        public String param1;
    }
}
