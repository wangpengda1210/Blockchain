package blockchain;

import java.io.Serializable;
import java.security.*;

public class Message implements Serializable {

    private final int id;
    private final String text;
    private final PublicKey publicKey;
    private final byte[] signature;

    public Message(String text, GenerateKeys generateKeys) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        id = BlockChain.getMessageId();
        this.text = text;
        generateKeys.createKeys();
        this.publicKey = generateKeys.getPublicKey();
        this.signature = SignatureUtil.sign(this.text + id, generateKeys.getPrivateKey());
    }

    public int getId() {
        return id;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public byte[] getSignature() {
        return signature;
    }

    public String getText() {
        return text;
    }

}
