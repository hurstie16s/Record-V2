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
 */
public class RSA {

    public static KeyPair generateRSAKeys() {
        KeyPair pair = null;
        try {
            var generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            pair = generator.generateKeyPair();
        } catch (Exception e) {
          handleError(e);
        }
        return pair;
    }

  public static PublicKey decodePublicKey(byte[] encodedKey) {
      PublicKey publicKey = null;
      try {
        publicKey = KeyFactory.getInstance("RSA").generatePublic(new PKCS8EncodedKeySpec(encodedKey));
      } catch (Exception e) {
        handleError(e);
      }
      return publicKey;
  }
    public static PrivateKey decodePrivateKey(byte[] encodedKey) {
      PrivateKey privateKey = null;
      try {
        privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
      } catch (Exception e) {
        handleError(e);
      }
      return privateKey;
    }

    public static byte[] encrypt(byte[] dataToEncrypt, PublicKey publicKey) {
        byte[] encryptedData = null;
        try {
            var cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            encryptedData = cipher.doFinal(dataToEncrypt);
        } catch (Exception e) {
          handleError(e);
        }
        return encryptedData;
    }

    public static byte[] decrypt(byte[] dataToDecrypt, PrivateKey privateKey) {
        byte[] decryptedData = null;
        try {
            var cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            decryptedData = cipher.doFinal(dataToDecrypt);
        } catch (Exception e) {
            handleError(e);
        }
        return decryptedData;
    }

  private static void handleError (Exception e) {
    var exitCode = 001;
    if (e instanceof NoSuchAlgorithmException) {
      exitCode = 201;
    } else if (e instanceof InvalidKeySpecException) {
      exitCode = 202;
    } else if (e instanceof NoSuchPaddingException) {
      exitCode = 203;
    } else if (e instanceof InvalidKeyException) {
      exitCode = 204;
    } else if (e instanceof BadPaddingException) {
      exitCode = 205;
    } else if (e instanceof IllegalBlockSizeException) {
      exitCode = 206;
    }
    System.out.println(e.getMessage());
    System.exit(exitCode);
  }

}
