/**
 * Decoder for decrypting the password
 *
 * @author Markus Meier
 * @version 1.0
 *
 */
package com.meier.markus.HelperClasses;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionWorker {

  /**
   * Class properties repository for web element detection
   * 
   * @Class property cipher => Instance of Cipher
   * @Class property ENCODED_KEY => Key String for decrypting the password
   */
  private static Cipher cipher;
  private static final String ENCODED_KEY = "+wPjsRGVxCjyvI0a3PDQQA==";

  /**
   * Decryption of password
   *
   * @param dataToDecrypt => Encrypted string to decrypt
   * 
   * @return decrypted text to provide to login method
   */
  public static String decrypt2String(String dataToDecrypt) {
    try {

      byte[] decodedKey = Base64.getDecoder().decode(ENCODED_KEY);

      SecretKey myKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
      cipher = Cipher.getInstance("AES");

      byte[] encryptedData = dataToDecrypt.getBytes();
      cipher.init(Cipher.DECRYPT_MODE, myKey);
      byte[] textDecrypted = cipher.doFinal(encryptedData);
      String decryptedText = new String(textDecrypted);
      return decryptedText;

    } catch (Exception e) {
      ErrorHandler.markTestCaseAsFailed(e);
      return null;
    }

  }
}
