package Security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Encrypter {

    private String key;
    private Key AESKey;
    private Cipher cipher;
    private byte[] encrypted;

    public Encrypter(String key) throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.key = key;
        this.AESKey = new SecretKeySpec(key.getBytes(), "DES");
        this.cipher = Cipher.getInstance("DES");
    }

    public String encrypt(String str) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, AESKey);
            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes("UTF8");

            // Encrypt
            byte[] enc = cipher.doFinal(utf8);

            // Encode bytes to base64 to get a string
            return new sun.misc.BASE64Encoder().encode(enc);
        } catch (java.io.IOException e) {
        }
        return null;
    }

    public String decrypt(String str) throws InvalidKeyException {
        try {
            cipher.init(Cipher.DECRYPT_MODE, AESKey);
            // Decode base64 to get bytes
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

            // Decrypt
            byte[] utf8 = cipher.doFinal(dec);

            // Decode using utf-8
            return new String(utf8, "UTF8");
        } catch (javax.crypto.BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (java.io.IOException e) {
        }
        return null;
    }
}
