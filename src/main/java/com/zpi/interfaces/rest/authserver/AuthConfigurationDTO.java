package com.zpi.interfaces.rest.authserver;


import com.zpi.domain.authserver.AuthServerConfiguration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthConfigurationDTO {
    TokenConfigurationDTO token;

    public static AuthConfigurationDTO fromDomain(AuthServerConfiguration configuration) {
        return new AuthConfigurationDTO(new TokenConfigurationDTO(configuration.getTokenExpirationTime(), configuration.getTokenSecretKey()));
    }

    public AuthServerConfiguration toDomain() {
        return new AuthServerConfiguration(token.getExpirationTime(), token.getSecretKey());
    }
}
