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
    public static final String BINARY = "6080604052600436106100345760003560e01c80632d0335ab1461003957806347153f8214610076578063bf5d3bdb146100a7575b600080fd5b34801561004557600080fd5b50610060600480360381019061005b91906109cc565b6100e4565b60405161006d9190610a12565b60405180910390f35b610090600480360381019061008b9190610ab6565b61012c565b60405161009e929190610bdd565b60405180910390f35b3480156100b357600080fd5b506100ce60048036038101906100c99190610ab6565b61034e565b6040516100db9190610c0d565b60405180910390f35b60008060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b6000606061013b85858561034e565b61017a576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161017190610cab565b60405180910390fd5b6001856080013561018b9190610cfa565b6000808760000160208101906101a191906109cc565b73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055507fd03bf592f687a60523d2ddd30db6948ef1c7934c57c181027cd0f2b9049c4adc8560405161020e9190610f20565b60405180910390a160008086602001602081019061022c91906109cc565b73ffffffffffffffffffffffffffffffffffffffff1687606001358860400135898060a0019061025c9190610f51565b8b600001602081019061026f91906109cc565b6040516020016102819392919061102c565b60405160208183030381529060405260405161029d9190611087565b600060405180830381858888f193505050503d80600081146102db576040519150601f19603f3d011682016040523d82523d6000602084013e6102e0565b606091505b5091509150603f87606001356102f691906110cd565b5a116102fe57fe5b8161033e576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016103359061114a565b60405180910390fd5b8181935093505050935093915050565b60008061045784848080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f820116905080830192505050505050506104497fdd8f4b70b0f4393e889bd39128a30628a78b61816a9eb8199759e7a349657e488860000160208101906103d091906109cc565b8960200160208101906103e391906109cc565b8a604001358b606001358c608001358d8060a001906104029190610f51565b60405161041092919061116a565b604051809103902060405160200161042e97969594939291906111ab565b60405160208183030381529060405280519060200120610503565b61051d90919063ffffffff16565b9050846080013560008087600001602081019061047491906109cc565b73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020541480156104f957508460000160208101906104ca91906109cc565b73ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16145b9150509392505050565b6000610516610510610544565b8361065e565b9050919050565b600080600061052c8585610691565b91509150610539816106e2565b819250505092915050565b60007f000000000000000000000000f2b8a3d6291a869a836fc376a13186f59f810c7e73ffffffffffffffffffffffffffffffffffffffff163073ffffffffffffffffffffffffffffffffffffffff161480156105c057507f000000000000000000000000000000000000000000000000000000000000005a46145b156105ed577f6e36fe73539203857730d48e38df9b2c63c61333b13e299b0e53195764775f5c905061065b565b6106587f8b73c3c69bb8fe3d512ecc4cf759cc79239f7b179b0ffacaa9a75d522b39400f7f9e0923a39f515e9a8cebc9fb694b9abf7e4b8c3f7ab6f81b56eabdac504b08dc7fae209a0b48f21c054280f2455d32cf309387644879d9acbd8ffc199163811885610848565b90505b90565b60008282604051602001610673929190611292565b60405160208183030381529060405280519060200120905092915050565b60008060418351036106d25760008060006020860151925060408601519150606086015160001a90506106c687828585610882565b945094505050506106db565b60006002915091505b9250929050565b600060048111156106f6576106f56112c9565b5b816004811115610709576107086112c9565b5b03156108455760016004811115610723576107226112c9565b5b816004811115610736576107356112c9565b5b03610776576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161076d90611344565b60405180910390fd5b6002600481111561078a576107896112c9565b5b81600481111561079d5761079c6112c9565b5b036107dd576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016107d4906113b0565b60405180910390fd5b600360048111156107f1576107f06112c9565b5b816004811115610804576108036112c9565b5b03610844576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161083b90611442565b60405180910390fd5b5b50565b60008383834630604051602001610863959493929190611462565b6040516020818303038152906040528051906020012090509392505050565b6000807f7fffffffffffffffffffffffffffffff5d576e7357a4501ddfe92f46681b20a08360001c11156108bd57600060039150915061095b565b6000600187878787604051600081526020016040526040516108e294939291906114d1565b6020604051602081039080840390855afa158015610904573d6000803e3d6000fd5b505050602060405103519050600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16036109525760006001925092505061095b565b80600092509250505b94509492505050565b600080fd5b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60006109998261096e565b9050919050565b6109a98161098e565b81146109b457600080fd5b50565b6000813590506109c6816109a0565b92915050565b6000602082840312156109e2576109e1610964565b5b60006109f0848285016109b7565b91505092915050565b6000819050919050565b610a0c816109f9565b82525050565b6000602082019050610a276000830184610a03565b92915050565b600080fd5b600060c08284031215610a4857610a47610a2d565b5b81905092915050565b600080fd5b600080fd5b600080fd5b60008083601f840112610a7657610a75610a51565b5b8235905067ffffffffffffffff811115610a9357610a92610a56565b5b602083019150836001820283011115610aaf57610aae610a5b565b5b9250929050565b600080600060408486031215610acf57610ace610964565b5b600084013567ffffffffffffffff811115610aed57610aec610969565b5b610af986828701610a32565b935050602084013567ffffffffffffffff811115610b1a57610b19610969565b5b610b2686828701610a60565b92509250509250925092565b60008115159050919050565b610b4781610b32565b82525050565b600081519050919050565b600082825260208201905092915050565b60005b83811015610b87578082015181840152602081019050610b6c565b60008484015250505050565b6000601f19601f8301169050919050565b6000610baf82610b4d565b610bb98185610b58565b9350610bc9818560208601610b69565b610bd281610b93565b840191505092915050565b6000604082019050610bf26000830185610b3e565b8181036020830152610c048184610ba4565b90509392505050565b6000602082019050610c226000830184610b3e565b92915050565b600082825260208201905092915050565b7f4d696e696d616c466f727761726465723a207369676e617475726520646f657360008201527f206e6f74206d6174636820726571756573740000000000000000000000000000602082015250565b6000610c95603283610c28565b9150610ca082610c39565b604082019050919050565b60006020820190508181036000830152610cc481610c88565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000610d05826109f9565b9150610d10836109f9565b9250828201905080821115610d2857610d27610ccb565b5b92915050565b6000610d3d60208401846109b7565b905092915050565b610d4e8161098e565b82525050565b610d5d816109f9565b8114610d6857600080fd5b50565b600081359050610d7a81610d54565b92915050565b6000610d8f6020840184610d6b565b905092915050565b610da0816109f9565b82525050565b600080fd5b600080fd5b600080fd5b60008083356001602003843603038112610dd257610dd1610db0565b5b83810192508235915060208301925067ffffffffffffffff821115610dfa57610df9610da6565b5b600182023603831315610e1057610e0f610dab565b5b509250929050565b600082825260208201905092915050565b82818337600083830152505050565b6000610e448385610e18565b9350610e51838584610e29565b610e5a83610b93565b840190509392505050565b600060c08301610e786000840184610d2e565b610e856000860182610d45565b50610e936020840184610d2e565b610ea06020860182610d45565b50610eae6040840184610d80565b610ebb6040860182610d97565b50610ec96060840184610d80565b610ed66060860182610d97565b50610ee46080840184610d80565b610ef16080860182610d97565b50610eff60a0840184610db5565b85830360a0870152610f12838284610e38565b925050508091505092915050565b60006020820190508181036000830152610f3a8184610e65565b905092915050565b600080fd5b600080fd5b600080fd5b60008083356001602003843603038112610f6e57610f6d610f42565b5b80840192508235915067ffffffffffffffff821115610f9057610f8f610f47565b5b602083019250600182023603831315610fac57610fab610f4c565b5b509250929050565b600081905092915050565b6000610fcb8385610fb4565b9350610fd8838584610e29565b82840190509392505050565b60008160601b9050919050565b6000610ffc82610fe4565b9050919050565b600061100e82610ff1565b9050919050565b6110266110218261098e565b611003565b82525050565b6000611039828587610fbf565b91506110458284611015565b601482019150819050949350505050565b600061106182610b4d565b61106b8185610fb4565b935061107b818560208601610b69565b80840191505092915050565b60006110938284611056565b915081905092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601260045260246000fd5b60006110d8826109f9565b91506110e3836109f9565b9250826110f3576110f261109e565b5b828204905092915050565b7f65786563757465206973206661696c0000000000000000000000000000000000600082015250565b6000611134600f83610c28565b915061113f826110fe565b602082019050919050565b6000602082019050818103600083015261116381611127565b9050919050565b6000611177828486610fbf565b91508190509392505050565b6000819050919050565b61119681611183565b82525050565b6111a58161098e565b82525050565b600060e0820190506111c0600083018a61118d565b6111cd602083018961119c565b6111da604083018861119c565b6111e76060830187610a03565b6111f46080830186610a03565b61120160a0830185610a03565b61120e60c083018461118d565b98975050505050505050565b600081905092915050565b7f1901000000000000000000000000000000000000000000000000000000000000600082015250565b600061125b60028361121a565b915061126682611225565b600282019050919050565b6000819050919050565b61128c61128782611183565b611271565b82525050565b600061129d8261124e565b91506112a9828561127b565b6020820191506112b9828461127b565b6020820191508190509392505050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602160045260246000fd5b7f45434453413a20696e76616c6964207369676e61747572650000000000000000600082015250565b600061132e601883610c28565b9150611339826112f8565b602082019050919050565b6000602082019050818103600083015261135d81611321565b9050919050565b7f45434453413a20696e76616c6964207369676e6174757265206c656e67746800600082015250565b600061139a601f83610c28565b91506113a582611364565b602082019050919050565b600060208201905081810360008301526113c98161138d565b9050919050565b7f45434453413a20696e76616c6964207369676e6174757265202773272076616c60008201527f7565000000000000000000000000000000000000000000000000000000000000602082015250565b600061142c602283610c28565b9150611437826113d0565b604082019050919050565b6000602082019050818103600083015261145b8161141f565b9050919050565b600060a082019050611477600083018861118d565b611484602083018761118d565b611491604083018661118d565b61149e6060830185610a03565b6114ab608083018461119c565b9695505050505050565b600060ff82169050919050565b6114cb816114b5565b82525050565b60006080820190506114e6600083018761118d565b6114f360208301866114c2565b611500604083018561118d565b61150d606083018461118d565b9594505050505056fea2646970667358221220d9239ad072c63a8e4711176dd7323b7329896a9334b0ec1a98ee8c42171c35d264736f6c63430008120033";

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
