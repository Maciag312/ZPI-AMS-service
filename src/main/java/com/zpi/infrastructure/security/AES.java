package com.zpi.infrastructure.security;

public interface AES {
    String encrypt(String message);
    String decrypt(String message);
}
