package nhom26;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Config {
    private final SecretKey key;
    private final  SecretKey keyAES;

    public Config() {
        String keyString = "#@123456";
        String keyAES = "!@34567712345678";
        byte[] keyBytes = keyString.getBytes();
        byte[] keyAESBytes = keyAES.getBytes();
        this.key = new SecretKeySpec(keyBytes, "DES");
        this.keyAES = new SecretKeySpec(keyAESBytes, "AES");
    }

    public SecretKey getKey() {
        return key;
    }

    public SecretKey getKeyAES() {
        return keyAES;
    }
}
