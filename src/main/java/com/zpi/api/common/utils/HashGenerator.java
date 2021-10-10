package com.zpi.api.common.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {

    private MessageDigest digest;

    public HashGenerator() {
        try {
            String algorithm = "SHA-256";
            digest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException ignored) {
        }
    }

    public String generate(String originalString) {
        byte[] hashed = digest.digest(
                originalString.getBytes(StandardCharsets.UTF_8));

        return bytesToHex(hashed);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);

        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
