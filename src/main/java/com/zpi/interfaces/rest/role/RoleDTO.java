package com.zpi.interfaces.rest.role;

import com.zpi.domain.role.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
class RoleDTO {
    private String role;
    private String color;
    private Set<AssignPermissionDTO> permissions;

    public static RoleDTO fromDomain(Role role) {
        var permissions = role.getPermissions().stream().map(AssignPermissionDTO::fromDomain).collect(Collectors.toSet());
        return new RoleDTO(role.getName(), role.getColor(), permissions);
    }
}
