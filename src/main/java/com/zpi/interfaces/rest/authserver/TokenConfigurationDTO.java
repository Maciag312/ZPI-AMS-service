package com.zpi.interfaces.rest.authserver;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenConfigurationDTO {
    long expirationTime;
    String secretKey;
}
