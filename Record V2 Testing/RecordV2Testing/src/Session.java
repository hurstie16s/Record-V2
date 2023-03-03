import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Base64;

/**
 * The type Session.
 */
public class Session {

    private String username;
    private String email;
    private final SecretKeySpec aesKey;
    private final PublicKey publicRSAKey;
    private final PrivateKey privateRSAKey;

    public Session(String username, String password, byte[] encryptedEmail, byte[] encodedPublicRSAKey, byte[] encryptedPrivateRSAKey) {
        this.username = username;
        this.aesKey = AES.generateInformedAESKey(password, username);
        this.email = new String(AES.decrypt(encryptedEmail, aesKey));
        this.publicRSAKey = RSA.decodePublicKey(encodedPublicRSAKey);
        this.privateRSAKey = RSA.decodePrivateKey(AES.decrypt(encryptedPrivateRSAKey, aesKey));
    }

    //getters
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public PublicKey getPublicRSAKey() {
        return publicRSAKey;
    }

    public PrivateKey getPrivateRSAKey() {
        return privateRSAKey;
    }

    public SecretKeySpec getAesKey() {
        return aesKey;
    }

    //setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
