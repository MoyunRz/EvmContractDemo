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
    public static final String BINARY = "6080604052600060045534801561001557600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610cc0806100656000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c8063420d65ab14610046578063b346cfb114610062578063d7465fd814610092575b600080fd5b610060600480360381019061005b91906105ed565b6100c2565b005b61007c60048036038101906100779190610694565b610275565b60405161008991906106dc565b60405180910390f35b6100ac60048036038101906100a7919061072d565b6103b1565b6040516100b991906107d9565b60405180910390f35b600360003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff168061016557503373ffffffffffffffffffffffffffffffffffffffff1660008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16145b6101a4576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161019b90610847565b60405180910390fd5b6000600454826040516020016101bb9291906108ce565b604051602081830303815290604052805190602001209050816001600083815260200190815260200160002090816101f39190610b02565b5060016002600083815260200190815260200160002060006101000a81548160ff0219169083151502179055507f4665aef0905d7d6f5b12ed7500efd9957ba62f72d0a02445df1131e374c06d698183604051610251929190610be3565b60405180910390a16004600081548092919061026c90610c42565b91905055505050565b60003373ffffffffffffffffffffffffffffffffffffffff1660008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614610305576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016102fc90610847565b60405180910390fd5b6001600360008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff021916908315150217905550600360008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff169050919050565b60606002600083815260200190815260200160002060009054906101000a900460ff166103ef5760405180602001604052806000815250905061048e565b60016000838152602001908152602001600020805461040d90610925565b80601f016020809104026020016040519081016040528092919081815260200182805461043990610925565b80156104865780601f1061045b57610100808354040283529160200191610486565b820191906000526020600020905b81548152906001019060200180831161046957829003601f168201915b505050505090505b919050565b6000604051905090565b600080fd5b600080fd5b600080fd5b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6104fa826104b1565b810181811067ffffffffffffffff82111715610519576105186104c2565b5b80604052505050565b600061052c610493565b905061053882826104f1565b919050565b600067ffffffffffffffff821115610558576105576104c2565b5b610561826104b1565b9050602081019050919050565b82818337600083830152505050565b600061059061058b8461053d565b610522565b9050828152602081018484840111156105ac576105ab6104ac565b5b6105b784828561056e565b509392505050565b600082601f8301126105d4576105d36104a7565b5b81356105e484826020860161057d565b91505092915050565b6000602082840312156106035761060261049d565b5b600082013567ffffffffffffffff811115610621576106206104a2565b5b61062d848285016105bf565b91505092915050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b600061066182610636565b9050919050565b61067181610656565b811461067c57600080fd5b50565b60008135905061068e81610668565b92915050565b6000602082840312156106aa576106a961049d565b5b60006106b88482850161067f565b91505092915050565b60008115159050919050565b6106d6816106c1565b82525050565b60006020820190506106f160008301846106cd565b92915050565b6000819050919050565b61070a816106f7565b811461071557600080fd5b50565b60008135905061072781610701565b92915050565b6000602082840312156107435761074261049d565b5b600061075184828501610718565b91505092915050565b600081519050919050565b600082825260208201905092915050565b60005b83811015610794578082015181840152602081019050610779565b60008484015250505050565b60006107ab8261075a565b6107b58185610765565b93506107c5818560208601610776565b6107ce816104b1565b840191505092915050565b600060208201905081810360008301526107f381846107a0565b905092915050565b7f596f7520617265207065726d697373696f6e2064656e69656400000000000000600082015250565b6000610831601983610765565b915061083c826107fb565b602082019050919050565b6000602082019050818103600083015261086081610824565b9050919050565b6000819050919050565b6000819050919050565b61088c61088782610867565b610871565b82525050565b600081905092915050565b60006108a88261075a565b6108b28185610892565b93506108c2818560208601610776565b80840191505092915050565b60006108da828561087b565b6020820191506108ea828461089d565b91508190509392505050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b6000600282049050600182168061093d57607f821691505b6020821081036109505761094f6108f6565b5b50919050565b60008190508160005260206000209050919050565b60006020601f8301049050919050565b600082821b905092915050565b6000600883026109b87fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8261097b565b6109c2868361097b565b95508019841693508086168417925050509392505050565b6000819050919050565b60006109ff6109fa6109f584610867565b6109da565b610867565b9050919050565b6000819050919050565b610a19836109e4565b610a2d610a2582610a06565b848454610988565b825550505050565b600090565b610a42610a35565b610a4d818484610a10565b505050565b5b81811015610a7157610a66600082610a3a565b600181019050610a53565b5050565b601f821115610ab657610a8781610956565b610a908461096b565b81016020851015610a9f578190505b610ab3610aab8561096b565b830182610a52565b50505b505050565b600082821c905092915050565b6000610ad960001984600802610abb565b1980831691505092915050565b6000610af28383610ac8565b9150826002028217905092915050565b610b0b8261075a565b67ffffffffffffffff811115610b2457610b236104c2565b5b610b2e8254610925565b610b39828285610a75565b600060209050601f831160018114610b6c5760008415610b5a578287015190505b610b648582610ae6565b865550610bcc565b601f198416610b7a86610956565b60005b82811015610ba257848901518255600182019150602085019450602081019050610b7d565b86831015610bbf5784890151610bbb601f891682610ac8565b8355505b6001600288020188555050505b505050505050565b610bdd816106f7565b82525050565b6000604082019050610bf86000830185610bd4565b8181036020830152610c0a81846107a0565b90509392505050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000610c4d82610867565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8203610c7f57610c7e610c13565b5b60018201905091905056fea2646970667358221220a0b12f5ae1117948db812a1b6046e4253637cfce40b4dc6b24159dec4f326f3a64736f6c63430008120033";

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
