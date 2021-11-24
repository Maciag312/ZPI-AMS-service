package com.zpi.infrastructure.persistance.user;

import com.zpi.domain.role.Role;
import com.zpi.domain.user.User;
import com.zpi.infrastructure.common.EntityTuple;
import com.zpi.infrastructure.persistance.role.RoleTuple;
import lombok.Data;
import lombok.Getter;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Data
class UserTuple implements EntityTuple<User> {

    private final String username;
    private final String password;
    private final String email;
    private final Set<RoleTuple> roles;
    private final boolean isActive;
    private final Map<String, String> attributes;


    UserTuple(User user) {
        username = user.getUsername();
        email = user.getEmail();
        password = user.getPassword();
        roles = user.getRoles().stream()
                .map(RoleTuple::fromDomain)
                .collect(Collectors.toSet());

        isActive = user.isActive();
        attributes = user.getAttributes();
    }

    @Override
    public User toDomain() {
        Set<Role> domainRoles = roles.stream().map(RoleTuple::toDomain).collect(Collectors.toSet());
        var user =  User.builder()
                .username(username)
                .email(email)
                .password(password)
                .attributes(attributes)
                .isActive(isActive)
                .build();
        user.getRoles().addAll(domainRoles);
        return user;
    }
}
