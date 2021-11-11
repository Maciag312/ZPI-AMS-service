package com.zpi.infrastructure.persistance.user;


import com.zpi.domain.user.OneTimePassword;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static java.time.ZoneOffset.*;

@Getter
@Data
public class OneTimePasswordTuple {
    private final String password;
    private final String email;
    private final long expiresAt;

    public OneTimePassword toDomain(){
        return new OneTimePassword(email, LocalDateTime.ofEpochSecond(expiresAt, 0, UTC), password, null);
    }
    public static OneTimePasswordTuple fromDomain(OneTimePassword otp){
        return new OneTimePasswordTuple(otp.getPassword(), otp.getEmail(), otp.getExpiresAt().toEpochSecond(UTC));
    }
}
