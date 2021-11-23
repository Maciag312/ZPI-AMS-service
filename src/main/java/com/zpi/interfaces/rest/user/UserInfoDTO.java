package com.zpi.interfaces.rest.user;

import com.zpi.domain.role.Role;
import com.zpi.domain.user.UserInfo;
import com.zpi.interfaces.rest.permission.PermissionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
    String email;
    Set<PermissionDTO> permissions;
    Set<String> roles;
    Map<String, String> attributes;
    boolean isActive;

    static UserInfoDTO fromDomain(UserInfo userInfo) {
        var permissions = userInfo.getPermissions().stream()
                .map(PermissionDTO::fromDomain)
                .collect(Collectors.toSet());

        var roles = userInfo.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toUnmodifiableSet());
        return new UserInfoDTO(userInfo.getEmail(), permissions, roles, userInfo.getAttributes(),userInfo.isActive());
    }
}
