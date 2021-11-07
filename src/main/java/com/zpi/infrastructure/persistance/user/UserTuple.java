package com.zpi.infrastructure.persistance.user;

import com.zpi.domain.role.Role;
import com.zpi.domain.user.User;
import com.zpi.infrastructure.common.EntityTuple;
import com.zpi.infrastructure.persistance.role.RoleTuple;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Data
class UserTuple implements EntityTuple<User> {

    private final String username;
    private final String password;
    private final String email;
    private final Set<RoleTuple> roles;


    UserTuple(User user) {
        username = user.getUsername();
        email = user.getEmail();
        password = user.getPassword();
        roles = user.getRoles().stream()
                .map(RoleTuple::fromDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public User toDomain() {
        Set<Role> domainRoles = roles.stream().map(RoleTuple::toDomain).collect(Collectors.toSet());
        var user =  User.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();
        user.getRoles().addAll(domainRoles);
        return user;
    }
}
