package com.zpi.infrastructure.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class AESImpl implements AES {
    private final String ALGO = "AES";

    @Value("${security.aes.secret-key}")
    private String secretKey;

    public String encrypt(String data) {
        if (data == null)
            return "";

        try {
            var key = generateKey();
            Cipher cipher = Cipher.getInstance(ALGO);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encVal);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            System.out.println(e);
        }
        return data;
    }

    public String decrypt(String encryptedData) {
        if (encryptedData == null)
            return "";

        try {
            var key = generateKey();
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decodedValue = Base64.getDecoder().decode(encryptedData);
            byte[] decValue = c.doFinal(decodedValue);
            return new String(decValue);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            System.out.println(e);
        }
        return encryptedData;
    }

    private Key generateKey() throws InvalidKeyException {
        final var secretKeyMinLength = 280;

        if (secretKey == null || secretKey.length() < secretKeyMinLength) {
            throw new InvalidKeyException("Incorrect secret key format");
        }

        return new SecretKeySpec(secretKey.getBytes(), ALGO);
    }
}