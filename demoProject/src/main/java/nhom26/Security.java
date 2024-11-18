package nhom26;

import javax.crypto.*;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Security {
    public Security() {
    }

    public String DESEncrypt(String password, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] bytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(bytes);

    }
    public String DESDecrypt(String encryptedPassword, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }
    public  String Sha256Hash(String input) {
        try {
            // Tạo đối tượng MessageDigest với thuật toán SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Băm chuỗi đầu vào
            byte[] encodedHash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // Chuyển kết quả băm sang dạng chuỗi hex
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean AESEncryptFile(String inputFile, SecretKey secretKey) throws Exception {
        File file = new File(inputFile);
        // Đọc nội dung file
        byte[] fileBytes = Files.readAllBytes(file.toPath());

        // Mã hóa nội dung file
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(fileBytes);

        // Ghi đè dữ liệu đã mã hóa vào file gốc
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(encryptedBytes);
            return  true;
        }
        catch (Exception e) {
            return false;
        }
    }
    public FileOrder AESDecryptFile(String inputFile, SecretKey secretKey) throws Exception {
        File file = new File(inputFile);
        // Read the encrypted file content
        byte[] encryptedBytes = Files.readAllBytes(file.toPath());

        // Initialize the cipher for decryption
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        // Convert the decrypted bytes to a string and print it
        String decryptedContent = new String(decryptedBytes, StandardCharsets.UTF_8);
        FileOrder attributes = parseAttributes(decryptedContent);
        System.out.println(attributes.toString());
        return attributes;
    }
    public FileOrder parseAttributes(String data) {
        FileOrder fileOrder = new FileOrder();

        // Split data by new lines
        String[] lines = data.split("\\r?\\n");

        for (String line : lines) {
            // Split each line into key and value by ": "
            String[] keyValue = line.split(": ", 2);

            // Ensure the line has both key and value
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();

                // Map each key to the appropriate field in FileOrder
                switch (key) {
                    case "idProduct":
                        fileOrder.setIdProduct(Integer.parseInt(value));
                        break;
                    case "userId":
                        fileOrder.setUserId(Integer.parseInt(value));
                        break;
                    case "receiver":
                        fileOrder.setReceiver(value);
                        break;
                    case "phoneNumber":
                        fileOrder.setPhoneNumber(value);
                        break;
                    case "quantity":
                        fileOrder.setQuantity(Integer.parseInt(value));
                        break;
                    case "totalPrice":
                        fileOrder.setTotalPrice(Double.parseDouble(value));
                        break;
                    case "address":
                        fileOrder.setAddress(value);
                        break;
                    case "type":
                        fileOrder.setType(value);
                        break;
                }
            }
        }
        return fileOrder;
    }


    public static void main(String[] args) throws Exception {
        Security security = new Security();
        Config config = new Config();
        FileOrder resp=  security.AESDecryptFile("E:\\Data\\orderf69282b9-f72d-4f86-ace5-a642df5bbd94.txt",config.getKeyAES());
    }

}
