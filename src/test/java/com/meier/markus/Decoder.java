package com.meier.markus;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Decoder {

  // private static final String UNICODE_FORMAT = "UTF-8";
  private static Cipher cipher;
  private static final String ENCODED_KEY = "+wPjsRGVxCjyvI0a3PDQQA==";
  private static final String ENCODED_TEXT = "kBK¬“f°.=Œ¥©Ííç=";


  public static String decrypt2String(byte[] dataToDecrypt) {
    try {

      byte[] decodedKey = Base64.getDecoder().decode(ENCODED_KEY);

      SecretKey myKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
      cipher = Cipher.getInstance("AES");

      byte[] encryptedData = ENCODED_TEXT.getBytes();
      cipher.init(Cipher.DECRYPT_MODE, myKey);
      byte[] textDecrypted = cipher.doFinal(encryptedData);
      String decryptedText = new String(textDecrypted);
      return decryptedText;

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }
}
