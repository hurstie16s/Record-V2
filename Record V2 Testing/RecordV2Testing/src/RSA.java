import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * RSA Encryption Class
 * Error Codes:
 * NoSuchAlgorithmException = 201
 * InvalidKeySpecException = 202
 * NoSuchPaddingException = 203
 * InvalidKeyException = 204
 * BadPaddingException = 205
 * IllegalBlockSizeException = 206
 *
 */
public class RSA {

    public static KeyPair generateRSAKeys() {
        KeyPair pair;
        try {
            var generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            pair = generator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return pair;
    }

  public static PublicKey decodePublicKey(byte[] encodedKey) {
    try {
      return KeyFactory.getInstance("RSA").generatePublic(new PKCS8EncodedKeySpec(encodedKey));
    } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }
    public static PrivateKey decodePrivateKey(byte[] encodedKey) {
        try {
            return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] encrypt(byte[] dataToEncrypt, PublicKey publicKey) {
        byte[] encryptedData;
        try {
            var cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            encryptedData = cipher.doFinal(dataToEncrypt);
        } catch (
                NoSuchPaddingException |
                NoSuchAlgorithmException |
                InvalidKeyException |
                BadPaddingException |
                IllegalBlockSizeException e
        ) {
            throw new RuntimeException(e);
        }
        return encryptedData;
    }

    public static byte[] decrypt(byte[] dataToDecrypt, PrivateKey privateKey) {
        byte[] decryptedData;
        try {
            var cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            decryptedData = cipher.doFinal(dataToDecrypt);
        } catch (
                NoSuchPaddingException |
                NoSuchAlgorithmException |
                InvalidKeyException |
                BadPaddingException |
                IllegalBlockSizeException e
        ) {
            throw new RuntimeException(e);
        }
        return decryptedData;
    }

}
