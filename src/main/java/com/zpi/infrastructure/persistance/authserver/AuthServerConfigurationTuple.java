package com.zpi.infrastructure.persistance.authserver;

import com.zpi.domain.authserver.AuthServerConfiguration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@AllArgsConstructor
public class AuthServerConfigurationTuple {
    long tokenExpirationTime;
    String tokenSecretKey;

    public static AuthServerConfigurationTuple fromDomain(AuthServerConfiguration configuration) {
        return new AuthServerConfigurationTuple(configuration.getTokenExpirationTime(), configuration.getTokenSecretKey());
    }

    public AuthServerConfiguration toDomain() {
        return new AuthServerConfiguration(tokenExpirationTime, tokenSecretKey);
    }
}
