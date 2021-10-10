package com.zpi.infrastructure.organization.user;

import com.zpi.domain.organization.user.User;
import com.zpi.infrastructure.common.EntityTuple;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
class UserTuple implements EntityTuple<User> {

    private final String login;
    private final String password;
    private final String organization;


    UserTuple(User user) {
        login = user.getLogin();
        password = user.getPassword();
        organization = user.getOrganization();
    }

    @Override
    public User toDomain() {
        return User.builder()
                .login(login)
                .password(password)
                .organization(organization)
                .build();
    }
}
