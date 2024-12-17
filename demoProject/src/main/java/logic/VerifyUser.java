package logic;
import java.io.*;
import java.nio.file.*;
import java.security.*;
import java.security.spec.*;

public class VerifyUser {
    private int userId;
    private String pathPrivate;
    private String order;
    private static final String ALGORITHM = "DSA";

    public VerifyUser(int userId, String pathPrivate, String order) {
        this.userId = userId;
        this.pathPrivate = pathPrivate;
        this.order = order;
    }

    public void sign() {
        try {
            // Tải private key từ file
            byte[] privateKeyBytes = Files.readAllBytes(Paths.get(pathPrivate));
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

            // Tạo đối tượng Signature
            Signature signer = Signature.getInstance(ALGORITHM);
            signer.initSign(privateKey, new SecureRandom());

            // Nội dung cần ký
            byte[] dataToSign = order.getBytes();
            signer.update(dataToSign);

            // Ký số
            byte[] signatureBytes = signer.sign();

            // Lưu chữ ký số ra file
            String signaturePath = String.format("E:/publicKeys/%d/signedOrder.sig", userId);
            Files.createDirectories(Paths.get(signaturePath).getParent());
            Files.write(Paths.get(signaturePath), signatureBytes);

            System.out.println("Ký tài liệu thành công!");
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi ký tài liệu: " + e.getMessage(), e);
        }
    }

    public boolean verify() {
        try {
            // Tải public key từ file
            String publicKeyPath = String.format("E:/publicKeys/%d/publicKey.bin", userId);
            byte[] publicKeyBytes = Files.readAllBytes(Paths.get(publicKeyPath));
            X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            PublicKey publicKey = keyFactory.generatePublic(spec);

            // Tải chữ ký từ file
            String signaturePath = String.format("E:/publicKeys/%d/signedOrder.sig", userId);
            byte[] signatureBytes = Files.readAllBytes(Paths.get(signaturePath));

            // Xác minh
            Signature signature = Signature.getInstance(ALGORITHM);
            signature.initVerify(publicKey);
            signature.update(order.getBytes());

            boolean isVerified = signature.verify(signatureBytes);
            System.out.println(isVerified ? "Tài liệu đã được xác minh!" : "Tài liệu không hợp lệ!");
            return isVerified;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi xác minh tài liệu: " + e.getMessage(), e);
        }
    }
}
