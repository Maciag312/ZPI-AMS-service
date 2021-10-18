package com.zpi.api.common.dto;

import com.zpi.domain.user.User;
import com.zpi.api.common.utils.HashGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotNull
    @NotEmpty
    private String login;

    @NotNull
    @NotEmpty
    private String password;

    public User toHashedDomain() {
        var generator = new HashGenerator();

        return User.builder()
                .login(generator.generate(login))
                .password(generator.generate(password))
                .build();
    }
}

