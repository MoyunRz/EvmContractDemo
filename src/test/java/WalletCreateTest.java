import org.junit.Test;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Hash;
import org.web3j.crypto.MnemonicUtils;
import org.web3j.utils.Numeric;

import java.security.SecureRandom;

/**
 * WalletCreateTest
 * 钱包地址创建
 */
public class WalletCreateTest {
    @Test
    public void TestAddrCreate() {
        final SecureRandom secureRandom =  new SecureRandom();
        for (int i = 0; i < 200; i++) {
            byte[] initialEntropy = new byte[16];
            secureRandom.nextBytes(initialEntropy);
            String mnemonic = MnemonicUtils.generateMnemonic(initialEntropy);
            byte[] seed = MnemonicUtils.generateSeed(mnemonic, "123456");
            System.out.println(mnemonic);
            ECKeyPair privateKey = ECKeyPair.create(Hash.sha256(seed));
            privateKey.getPrivateKey();
            Credentials credentials = Credentials.create(privateKey);
            System.out.println(credentials.getAddress());
            System.out.println( Numeric.toHexStringWithPrefix(credentials.getEcKeyPair().getPrivateKey()));
        }
    }
}
