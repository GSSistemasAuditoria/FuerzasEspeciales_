package com.auditorias.fuerzasespeciales.utils;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Base64.encodeBase64;

public class Encryption {

    private final static String alg = "AES";
    private final static String cI = "AES/CBC/PKCS5Padding";
    private final Key publicKey = null;
    private final Key privateKey = null;
    private final String key = "8080808080808080";
    private final String iv = "8080808080808080";

    public Encryption() {
    }

    public static byte[] HexStringToByteArray(String s) {
        byte[] data = new byte[s.length() / 2];
        for (int i = 0; i < s.length(); i += 2) {
            data[i / 2] = (Integer.decode("0x" + s.charAt(i) + s.charAt(i + 1))).byteValue();
        }
        return data;
    }

    public String encryptAES(String cleartext) {
        byte[] encrypted = new byte[0];
        if (cleartext != null) {
            try {
                Cipher cipher = Cipher.getInstance(cI);
                SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), alg);
                IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
                encrypted = cipher.doFinal(cleartext.getBytes());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (InvalidAlgorithmParameterException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }
        }
        return new String(encodeBase64(encrypted));
    }

    public String decryptAES(String encrypted) {
        byte[] decrypted = new byte[0];
        if (encrypted != null) {
            try {
                Cipher cipher = Cipher.getInstance(cI);
                SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), alg);
                IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
                byte[] enc = decodeBase64(encrypted.getBytes());
                cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
                decrypted = cipher.doFinal(enc);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (InvalidAlgorithmParameterException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
                return encrypted;
            } catch (ArrayIndexOutOfBoundsException e) {
                return encrypted;
            }
        }
        return new String(decrypted);
    }
}
