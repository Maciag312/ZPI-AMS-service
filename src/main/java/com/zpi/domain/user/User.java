package com.zpi.domain.user;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@EqualsAndHashCode
public class User {
    private final String login;
    private final String password;

    @Setter
    private String organization;
}
