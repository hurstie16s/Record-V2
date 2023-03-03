public class PrimaryAccountFunctions {

  public static void logIn (String username, String password){}
  public static void changeUsername(String newUsername, Session accountSession){
    //TODO: Update Session, update database
  }
  public static void changeEmail(String newEmail, Session accountSession){
    /*
    TODO: Encrypt new email, update Session, update database
     */
  }
  public static void changePassword (String username, String oldPassword, String newPassword, Session accountSession){
    /*
    TODO:Update AES key
    TODO: Re-encrypt private RSA key, update database
    TODO: Re-encrypt email, update database
    TODO: Hash new password, update database
     */
  }
  /*
  User database table:
  username - String
  hashedPassword - byte[]
  hashedEmail - byte[]
  encodedPublicKey - byte[]
  encryptedEncodedPrivateKey - byte[]
   */

}
