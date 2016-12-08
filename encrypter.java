import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/*
How to use:
1. create instance of this class, parameter take in the key, the key is a 16 char string:
public Encrypter en = new Encrypter("CODEBOX-DEC-2016");
2. encrypt the string you need by calling encrypt method, pass in the password as parameter, the result is type byte[]:
byte[] newPassword = en.encrypt("heloohelloohello");
3. decrypt the byte array into readable string again by calling decrypt method, the byte array pass as parameter:
String originalPassword = en.decrypt(newPassword);
*/

public class Encrypter {
    private String key;
    private Key AESKey;
    private Cipher cipher;
    private byte[] encrypted;
    public Encrypter (String key) throws NoSuchAlgorithmException, NoSuchPaddingException{
        this.key = key;
        this.AESKey = new SecretKeySpec(key.getBytes(), "AES");
        this.cipher = Cipher.getInstance("AES");
    }
    
    public byte[] encrypt (String password) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        cipher.init(Cipher.ENCRYPT_MODE, AESKey);
        encrypted = cipher.doFinal(password.getBytes());
        return encrypted;
    }
        
    public String decrypt (byte[] password) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        cipher.init(Cipher.DECRYPT_MODE, AESKey);
        String decrypted = new String(cipher.doFinal(password));
        return decrypted;
    }