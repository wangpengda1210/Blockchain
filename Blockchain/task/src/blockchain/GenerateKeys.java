package blockchain;

import java.security.*;

public class GenerateKeys {

    private final KeyPairGenerator keyGen;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public GenerateKeys(int keyLength) throws NoSuchAlgorithmException {
        this.keyGen = KeyPairGenerator.getInstance("RSA");
        this.keyGen.initialize(keyLength);
    }

    public void createKeys() {
        KeyPair keyPair = this.keyGen.generateKeyPair();
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

}
