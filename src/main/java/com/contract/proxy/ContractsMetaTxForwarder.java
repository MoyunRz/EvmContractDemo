package com.contract.proxy;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
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
public class ContractsMetaTxForwarder extends Contract {
    public static String BINARY = "";

    public static final String FUNC_EXECUTE = "execute";

    public static final String FUNC_GETNONCE = "getNonce";

    public static final String FUNC_VERIFY = "verify";

    public static final Event LOG_EVENT = new Event("log",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected ContractsMetaTxForwarder(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ContractsMetaTxForwarder(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ContractsMetaTxForwarder(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ContractsMetaTxForwarder(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<LogEventResponse> getLogEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LOG_EVENT, transactionReceipt);
        ArrayList<LogEventResponse> responses = new ArrayList<LogEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LogEventResponse typedResponse = new LogEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.param0 = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LogEventResponse> logEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, LogEventResponse>() {
            @Override
            public LogEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LOG_EVENT, log);
                LogEventResponse typedResponse = new LogEventResponse();
                typedResponse.log = log;
                typedResponse.param0 = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<LogEventResponse> logEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LOG_EVENT));
        return logEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> execute(ForwardRequest req, byte[] signature, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_EXECUTE,
                Arrays.<Type>asList(req,
                        new org.web3j.abi.datatypes.DynamicBytes(signature)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);

    }

    public RemoteFunctionCall<BigInteger> getNonce(String from) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETNONCE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> verify(ForwardRequest req, byte[] signature) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_VERIFY,
                Arrays.<Type>asList(req,
                        new org.web3j.abi.datatypes.DynamicBytes(signature)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    @Deprecated
    public static ContractsMetaTxForwarder load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ContractsMetaTxForwarder(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ContractsMetaTxForwarder load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ContractsMetaTxForwarder(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ContractsMetaTxForwarder load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ContractsMetaTxForwarder(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ContractsMetaTxForwarder load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ContractsMetaTxForwarder(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ContractsMetaTxForwarder> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ContractsMetaTxForwarder.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<ContractsMetaTxForwarder> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ContractsMetaTxForwarder.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ContractsMetaTxForwarder> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ContractsMetaTxForwarder.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ContractsMetaTxForwarder> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ContractsMetaTxForwarder.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class ForwardRequest extends DynamicStruct {
        public String from;

        public String to;

        public BigInteger value;

        public BigInteger gas;

        public BigInteger nonce;

        public byte[] data;

        public ForwardRequest(String from, String to, BigInteger value, BigInteger gas, BigInteger nonce, byte[] data) {
            super(new org.web3j.abi.datatypes.Address(160, from),
                    new org.web3j.abi.datatypes.Address(160, to),
                    new org.web3j.abi.datatypes.generated.Uint256(value),
                    new org.web3j.abi.datatypes.generated.Uint256(gas),
                    new org.web3j.abi.datatypes.generated.Uint256(nonce),
                    new org.web3j.abi.datatypes.DynamicBytes(data));
            this.from = from;
            this.to = to;
            this.value = value;
            this.gas = gas;
            this.nonce = nonce;
            this.data = data;
        }

        public ForwardRequest(Address from, Address to, Uint256 value, Uint256 gas, Uint256 nonce, DynamicBytes data) {
            super(from, to, value, gas, nonce, data);
            this.from = from.getValue();
            this.to = to.getValue();
            this.value = value.getValue();
            this.gas = gas.getValue();
            this.nonce = nonce.getValue();
            this.data = data.getValue();
        }
    }

    public static class LogEventResponse extends BaseEventResponse {
        public String param0;
    }
}
