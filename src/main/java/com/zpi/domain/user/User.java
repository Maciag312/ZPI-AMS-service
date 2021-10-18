package com.zpi.domain.user;

import com.zpi.domain.manager.Role;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
@EqualsAndHashCode
public class User {
    private final String login;
    private final String password;

    private Set<Role> roles;

}
