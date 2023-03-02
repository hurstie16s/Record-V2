import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class AES {

    private static final byte[] IV = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static final IvParameterSpec IVSPEC = new IvParameterSpec(IV);

    public static SecretKeySpec generateInformedAESKey(String secretKeySpec, String salt) {

        SecretKeySpec secretKey;
        try {
            var factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            var spec = new PBEKeySpec(secretKeySpec.toCharArray(), salt.getBytes(), 65536, 256);
            secretKey = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        return secretKey;
    }

    /*
    To Encrypt String:
    Base64.getEncoder().encodeToString(AES.encrypt(strToEncrypt.getBytes(StandardCharsets.UTF_8), key));
     */

    public static byte[] encrypt(byte[] dataToEncrypt, SecretKeySpec key) {

        byte[] encryptedData;
        try {
            var cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, IVSPEC);
            encryptedData = cipher.doFinal(dataToEncrypt);
        } catch (
                InvalidAlgorithmParameterException |
                NoSuchPaddingException |
                IllegalBlockSizeException |
                NoSuchAlgorithmException |
                BadPaddingException |
                InvalidKeyException e
        ) {
            throw new RuntimeException(e);
        }
        return encryptedData;
    }

    public static byte[] decrypt(byte[] dataToDecrypt, SecretKeySpec key) {

        byte[] decryptedData;
        try {
            var cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, IVSPEC);
            decryptedData = cipher.doFinal(dataToDecrypt);
        } catch (
                NoSuchPaddingException |
                NoSuchAlgorithmException |
                InvalidKeyException |
                InvalidAlgorithmParameterException |
                IllegalBlockSizeException |
                BadPaddingException e
        ) {
            throw new RuntimeException(e);
        }
        return decryptedData;
    }

}
