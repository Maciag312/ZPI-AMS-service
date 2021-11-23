package com.zpi.interfaces.rest.group;

import com.zpi.domain.permission.Permission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AssignPermissionDTO {
    private String permission;

    public static AssignPermissionDTO fromDomain(Permission permission) {
        return new AssignPermissionDTO(permission.getPermission());
    }

    public static Permission toDomain(AssignPermissionDTO permissionDTO) {
        return new Permission(permissionDTO.permission);
    }
}
