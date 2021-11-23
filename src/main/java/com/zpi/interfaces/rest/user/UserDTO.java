package com.zpi.interfaces.rest.user;


import com.zpi.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    String username;
    String email;
    boolean isActive;
    Map<String, String> attributes;

    public User toDomain() {
        return new User(username, email);
    }

    public static UserDTO fromDomain(User user) {
        return new UserDTO(user.getUsername(), user.getEmail(),  user.isActive(), user.getAttributes());
    }
}
