package alg;

import java.security.*;

public class DS {
    KeyPair keyPair;
    PrivateKey privateKey;
    PublicKey publicKey;
    Signature signature;
    SecureRandom secureRandom;

    public KeyPair genKey() throws Exception{
        KeyPairGenerator generator = KeyPairGenerator.getInstance("DSA");
        secureRandom = new SecureRandom();
        generator.initialize(2048, secureRandom); // 2048 4096
        keyPair = generator.generateKeyPair();
        signature = Signature.getInstance("SHA256withDSA");
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();
        return keyPair;
    }



}
