package com.zpi.domain.user;

import com.zpi.domain.role.Role;
import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
public class User {
    private final String username;
    private final String email;
    final Set<Role> roles = new HashSet<>();
    private String password;

    public String renewPassword(int length) {
        password = RandomStringUtils.randomAlphanumeric(length);
        return password;
    }
}
