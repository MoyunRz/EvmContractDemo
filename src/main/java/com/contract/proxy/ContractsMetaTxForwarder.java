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
    public static final String BINARY = "6080604052600436106100345760003560e01c80632d0335ab1461003957806347153f8214610076578063bf5d3bdb146100a7575b600080fd5b34801561004557600080fd5b50610060600480360381019061005b9190610995565b6100e4565b60405161006d91906109db565b60405180910390f35b610090600480360381019061008b9190610a7f565b61012c565b60405161009e929190610ba6565b60405180910390f35b3480156100b357600080fd5b506100ce60048036038101906100c99190610a7f565b610317565b6040516100db9190610bd6565b60405180910390f35b60008060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b6000606061013b858585610317565b61017a576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161017190610c74565b60405180910390fd5b6001856080013561018b9190610cc3565b6000808760000160208101906101a19190610995565b73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055506000808660200160208101906101f59190610995565b73ffffffffffffffffffffffffffffffffffffffff1687606001358860400135898060a001906102259190610d06565b8b60000160208101906102389190610995565b60405160200161024a93929190610df0565b6040516020818303038152906040526040516102669190610e4b565b600060405180830381858888f193505050503d80600081146102a4576040519150601f19603f3d011682016040523d82523d6000602084013e6102a9565b606091505b5091509150603f87606001356102bf9190610e91565b5a116102c757fe5b81610307576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016102fe90610f0e565b60405180910390fd5b8181935093505050935093915050565b60008061042084848080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f820116905080830192505050505050506104127fdd8f4b70b0f4393e889bd39128a30628a78b61816a9eb8199759e7a349657e488860000160208101906103999190610995565b8960200160208101906103ac9190610995565b8a604001358b606001358c608001358d8060a001906103cb9190610d06565b6040516103d9929190610f2e565b60405180910390206040516020016103f79796959493929190610f6f565b604051602081830303815290604052805190602001206104cc565b6104e690919063ffffffff16565b9050846080013560008087600001602081019061043d9190610995565b73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020541480156104c257508460000160208101906104939190610995565b73ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16145b9150509392505050565b60006104df6104d961050d565b83610627565b9050919050565b60008060006104f5858561065a565b91509150610502816106ab565b819250505092915050565b60007f0000000000000000000000006619b44a2e5cf39ee39e179fee5d281488b718c873ffffffffffffffffffffffffffffffffffffffff163073ffffffffffffffffffffffffffffffffffffffff1614801561058957507f000000000000000000000000000000000000000000000000000000000000238246145b156105b6577f6f934bfcc302eaca4ebabbd359c892ee234303174f79e5e61e4024c3a139f7649050610624565b6106217f8b73c3c69bb8fe3d512ecc4cf759cc79239f7b179b0ffacaa9a75d522b39400f7f9e0923a39f515e9a8cebc9fb694b9abf7e4b8c3f7ab6f81b56eabdac504b08dc7fae209a0b48f21c054280f2455d32cf309387644879d9acbd8ffc199163811885610811565b90505b90565b6000828260405160200161063c929190611056565b60405160208183030381529060405280519060200120905092915050565b600080604183510361069b5760008060006020860151925060408601519150606086015160001a905061068f8782858561084b565b945094505050506106a4565b60006002915091505b9250929050565b600060048111156106bf576106be61108d565b5b8160048111156106d2576106d161108d565b5b031561080e57600160048111156106ec576106eb61108d565b5b8160048111156106ff576106fe61108d565b5b0361073f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161073690611108565b60405180910390fd5b600260048111156107535761075261108d565b5b8160048111156107665761076561108d565b5b036107a6576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161079d90611174565b60405180910390fd5b600360048111156107ba576107b961108d565b5b8160048111156107cd576107cc61108d565b5b0361080d576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161080490611206565b60405180910390fd5b5b50565b6000838383463060405160200161082c959493929190611226565b6040516020818303038152906040528051906020012090509392505050565b6000807f7fffffffffffffffffffffffffffffff5d576e7357a4501ddfe92f46681b20a08360001c1115610886576000600391509150610924565b6000600187878787604051600081526020016040526040516108ab9493929190611295565b6020604051602081039080840390855afa1580156108cd573d6000803e3d6000fd5b505050602060405103519050600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff160361091b57600060019250925050610924565b80600092509250505b94509492505050565b600080fd5b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b600061096282610937565b9050919050565b61097281610957565b811461097d57600080fd5b50565b60008135905061098f81610969565b92915050565b6000602082840312156109ab576109aa61092d565b5b60006109b984828501610980565b91505092915050565b6000819050919050565b6109d5816109c2565b82525050565b60006020820190506109f060008301846109cc565b92915050565b600080fd5b600060c08284031215610a1157610a106109f6565b5b81905092915050565b600080fd5b600080fd5b600080fd5b60008083601f840112610a3f57610a3e610a1a565b5b8235905067ffffffffffffffff811115610a5c57610a5b610a1f565b5b602083019150836001820283011115610a7857610a77610a24565b5b9250929050565b600080600060408486031215610a9857610a9761092d565b5b600084013567ffffffffffffffff811115610ab657610ab5610932565b5b610ac2868287016109fb565b935050602084013567ffffffffffffffff811115610ae357610ae2610932565b5b610aef86828701610a29565b92509250509250925092565b60008115159050919050565b610b1081610afb565b82525050565b600081519050919050565b600082825260208201905092915050565b60005b83811015610b50578082015181840152602081019050610b35565b60008484015250505050565b6000601f19601f8301169050919050565b6000610b7882610b16565b610b828185610b21565b9350610b92818560208601610b32565b610b9b81610b5c565b840191505092915050565b6000604082019050610bbb6000830185610b07565b8181036020830152610bcd8184610b6d565b90509392505050565b6000602082019050610beb6000830184610b07565b92915050565b600082825260208201905092915050565b7f4d696e696d616c466f727761726465723a207369676e617475726520646f657360008201527f206e6f74206d6174636820726571756573740000000000000000000000000000602082015250565b6000610c5e603283610bf1565b9150610c6982610c02565b604082019050919050565b60006020820190508181036000830152610c8d81610c51565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000610cce826109c2565b9150610cd9836109c2565b9250828201905080821115610cf157610cf0610c94565b5b92915050565b600080fd5b600080fd5b600080fd5b60008083356001602003843603038112610d2357610d22610cf7565b5b80840192508235915067ffffffffffffffff821115610d4557610d44610cfc565b5b602083019250600182023603831315610d6157610d60610d01565b5b509250929050565b600081905092915050565b82818337600083830152505050565b6000610d8f8385610d69565b9350610d9c838584610d74565b82840190509392505050565b60008160601b9050919050565b6000610dc082610da8565b9050919050565b6000610dd282610db5565b9050919050565b610dea610de582610957565b610dc7565b82525050565b6000610dfd828587610d83565b9150610e098284610dd9565b601482019150819050949350505050565b6000610e2582610b16565b610e2f8185610d69565b9350610e3f818560208601610b32565b80840191505092915050565b6000610e578284610e1a565b915081905092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601260045260246000fd5b6000610e9c826109c2565b9150610ea7836109c2565b925082610eb757610eb6610e62565b5b828204905092915050565b7f65786563757465206973206661696c0000000000000000000000000000000000600082015250565b6000610ef8600f83610bf1565b9150610f0382610ec2565b602082019050919050565b60006020820190508181036000830152610f2781610eeb565b9050919050565b6000610f3b828486610d83565b91508190509392505050565b6000819050919050565b610f5a81610f47565b82525050565b610f6981610957565b82525050565b600060e082019050610f84600083018a610f51565b610f916020830189610f60565b610f9e6040830188610f60565b610fab60608301876109cc565b610fb860808301866109cc565b610fc560a08301856109cc565b610fd260c0830184610f51565b98975050505050505050565b600081905092915050565b7f1901000000000000000000000000000000000000000000000000000000000000600082015250565b600061101f600283610fde565b915061102a82610fe9565b600282019050919050565b6000819050919050565b61105061104b82610f47565b611035565b82525050565b600061106182611012565b915061106d828561103f565b60208201915061107d828461103f565b6020820191508190509392505050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602160045260246000fd5b7f45434453413a20696e76616c6964207369676e61747572650000000000000000600082015250565b60006110f2601883610bf1565b91506110fd826110bc565b602082019050919050565b60006020820190508181036000830152611121816110e5565b9050919050565b7f45434453413a20696e76616c6964207369676e6174757265206c656e67746800600082015250565b600061115e601f83610bf1565b915061116982611128565b602082019050919050565b6000602082019050818103600083015261118d81611151565b9050919050565b7f45434453413a20696e76616c6964207369676e6174757265202773272076616c60008201527f7565000000000000000000000000000000000000000000000000000000000000602082015250565b60006111f0602283610bf1565b91506111fb82611194565b604082019050919050565b6000602082019050818103600083015261121f816111e3565b9050919050565b600060a08201905061123b6000830188610f51565b6112486020830187610f51565b6112556040830186610f51565b61126260608301856109cc565b61126f6080830184610f60565b9695505050505050565b600060ff82169050919050565b61128f81611279565b82525050565b60006080820190506112aa6000830187610f51565b6112b76020830186611286565b6112c46040830185610f51565b6112d16060830184610f51565b9594505050505056fea26469706673582212206571f36522df35367df7ece16153660ed7fd4662377e35cd437e7ce9c46e988064736f6c63430008120033";

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
