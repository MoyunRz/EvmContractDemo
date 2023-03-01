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
    public static final String BINARY = "608060405234801561001057600080fd5b50600436106100415760003560e01c8063420d65ab14610046578063b346cfb114610062578063d7465fd814610092575b600080fd5b610060600480360381019061005b919061065b565b6100c2565b005b61007c60048036038101906100779190610702565b6102fa565b604051610089919061074a565b60405180910390f35b6100ac60048036038101906100a7919061079b565b610436565b6040516100b99190610847565b60405180910390f35b600560003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff168061016557503373ffffffffffffffffffffffffffffffffffffffff1660008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16145b6101a4576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161019b906108b5565b60405180910390fd5b6000600654826040516020016101bb92919061093c565b6040516020818303038152906040528051906020012090506001600301600082815260200190815260200160002060009054906101000a900460ff1615610237576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161022e906109b0565b60405180910390fd5b600173969ab160ae790fb5793aa989afe65e4a1c2656146390c6dbb1909183856040518463ffffffff1660e01b815260040161027593929190610a30565b60006040518083038186803b15801561028d57600080fd5b505af41580156102a1573d6000803e3d6000fd5b505050507f4665aef0905d7d6f5b12ed7500efd9957ba62f72d0a02445df1131e374c06d6981836040516102d6929190610a7d565b60405180910390a1600660008154809291906102f190610adc565b91905055505050565b60003373ffffffffffffffffffffffffffffffffffffffff1660008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161461038a576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610381906108b5565b60405180910390fd5b6001600560008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff021916908315150217905550600560008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff169050919050565b60606001600301600083815260200190815260200160002060009054906101000a900460ff16610477576040518060200160405280600081525090506104fc565b600173969ab160ae790fb5793aa989afe65e4a1c26561463f44f8e3b9091846040518363ffffffff1660e01b81526004016104b3929190610b24565b600060405180830381865af41580156104d0573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f820116820180604052508101906104f99190610bbd565b90505b919050565b6000604051905090565b600080fd5b600080fd5b600080fd5b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6105688261051f565b810181811067ffffffffffffffff8211171561058757610586610530565b5b80604052505050565b600061059a610501565b90506105a6828261055f565b919050565b600067ffffffffffffffff8211156105c6576105c5610530565b5b6105cf8261051f565b9050602081019050919050565b82818337600083830152505050565b60006105fe6105f9846105ab565b610590565b90508281526020810184848401111561061a5761061961051a565b5b6106258482856105dc565b509392505050565b600082601f83011261064257610641610515565b5b81356106528482602086016105eb565b91505092915050565b6000602082840312156106715761067061050b565b5b600082013567ffffffffffffffff81111561068f5761068e610510565b5b61069b8482850161062d565b91505092915050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60006106cf826106a4565b9050919050565b6106df816106c4565b81146106ea57600080fd5b50565b6000813590506106fc816106d6565b92915050565b6000602082840312156107185761071761050b565b5b6000610726848285016106ed565b91505092915050565b60008115159050919050565b6107448161072f565b82525050565b600060208201905061075f600083018461073b565b92915050565b6000819050919050565b61077881610765565b811461078357600080fd5b50565b6000813590506107958161076f565b92915050565b6000602082840312156107b1576107b061050b565b5b60006107bf84828501610786565b91505092915050565b600081519050919050565b600082825260208201905092915050565b60005b838110156108025780820151818401526020810190506107e7565b60008484015250505050565b6000610819826107c8565b61082381856107d3565b93506108338185602086016107e4565b61083c8161051f565b840191505092915050565b60006020820190508181036000830152610861818461080e565b905092915050565b7f596f7520617265207065726d697373696f6e2064656e69656400000000000000600082015250565b600061089f6019836107d3565b91506108aa82610869565b602082019050919050565b600060208201905081810360008301526108ce81610892565b9050919050565b6000819050919050565b6000819050919050565b6108fa6108f5826108d5565b6108df565b82525050565b600081905092915050565b6000610916826107c8565b6109208185610900565b93506109308185602086016107e4565b80840191505092915050565b600061094882856108e9565b602082019150610958828461090b565b91508190509392505050565b7f686173684964206973206e6f7420656d70747900000000000000000000000000600082015250565b600061099a6013836107d3565b91506109a582610964565b602082019050919050565b600060208201905081810360008301526109c98161098d565b9050919050565b8082525050565b6109e081610765565b82525050565b600082825260208201905092915050565b6000610a02826107c8565b610a0c81856109e6565b9350610a1c8185602086016107e4565b610a258161051f565b840191505092915050565b6000606082019050610a4560008301866109d0565b610a5260208301856109d7565b8181036040830152610a6481846109f7565b9050949350505050565b610a7781610765565b82525050565b6000604082019050610a926000830185610a6e565b8181036020830152610aa4818461080e565b90509392505050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000610ae7826108d5565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8203610b1957610b18610aad565b5b600182019050919050565b6000604082019050610b3960008301856109d0565b610b4660208301846109d7565b9392505050565b6000610b60610b5b846105ab565b610590565b905082815260208101848484011115610b7c57610b7b61051a565b5b610b878482856107e4565b509392505050565b600082601f830112610ba457610ba3610515565b5b8151610bb4848260208601610b4d565b91505092915050565b600060208284031215610bd357610bd261050b565b5b600082015167ffffffffffffffff811115610bf157610bf0610510565b5b610bfd84828501610b8f565b9150509291505056fea26469706673582212208f60437bd7bad563d7987196d5c513a5f535e4add51da60428b978d338a0148f64736f6c63430008120033";

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
