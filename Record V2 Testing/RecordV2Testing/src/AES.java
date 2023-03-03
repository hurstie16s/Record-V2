import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * AES encryption class.
 * Error Codes:
 * NoSuchAlgorithmException = 101
 * InvalidKeySpecException = 102
 * InvalidAlgorithmParameterException = 103
 * NoSuchPaddingException = 104
 * IllegalBlockSizeException = 105
 * BadPaddingException = 106
 * InvalidKeyException = 107
 */
public abstract class AES {

  //TODO: Create Error codes for each different error

    private static final byte[] IV = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static final IvParameterSpec IVSPEC = new IvParameterSpec(IV);

    public static SecretKeySpec generateInformedAESKey(String secretKeySpec, String salt) {

        SecretKeySpec secretKey = null;
        try {
            var factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            var spec = new PBEKeySpec(secretKeySpec.toCharArray(), salt.getBytes(), 65536, 256);
            secretKey = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
        } catch (Exception e) {
          handleError(e);
        }
        return secretKey;
    }

    /*
    To Encrypt String:
    Base64.getEncoder().encodeToString(AES.encrypt(strToEncrypt.getBytes(StandardCharsets.UTF_8), key));
     */

    public static byte[] encrypt(byte[] dataToEncrypt, SecretKeySpec key) {

        byte[] encryptedData = null;
        try {
            var cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, IVSPEC);
            encryptedData = cipher.doFinal(dataToEncrypt);
        } catch (Exception e) {
            handleError(e);
        }
        return encryptedData;
    }

    public static byte[] decrypt(byte[] dataToDecrypt, SecretKeySpec key) {

        byte[] decryptedData = null;
        try {
            var cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, IVSPEC);
            decryptedData = cipher.doFinal(dataToDecrypt);
        } catch (Exception e) {
            handleError(e);
        }
        return decryptedData;
    }
    private static void handleError (Exception e) {
      var exitCode = 000;
      if (e instanceof NoSuchAlgorithmException) {
        exitCode = 101;
      } else if (e instanceof InvalidKeySpecException) {
        exitCode = 102;
      } else if (e instanceof InvalidAlgorithmParameterException) {
        exitCode = 103;
      } else if (e instanceof NoSuchPaddingException) {
        exitCode = 104;
      } else if (e instanceof IllegalBlockSizeException) {
        exitCode = 105;
      } else if (e instanceof BadPaddingException) {
        exitCode = 106;
      } else if (e instanceof InvalidKeyException) {
        exitCode = 107;
      } else {
        exitCode = 001;
      }
      System.out.println(e.getMessage());
      System.exit(exitCode);
    }

}
