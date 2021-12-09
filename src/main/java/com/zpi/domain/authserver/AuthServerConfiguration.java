package com.zpi.domain.authserver;

import lombok.Value;

@Value
public class AuthServerConfiguration {
    long TokenExpirationTime;
    String TokenSecretKey;
}
