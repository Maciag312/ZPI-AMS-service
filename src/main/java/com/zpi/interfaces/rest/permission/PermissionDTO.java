package com.zpi.interfaces.rest.permission;

import com.zpi.domain.permission.Permission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDTO {
    private String permission;
    private boolean removable;

    public static PermissionDTO fromDomain(Permission permission) {
        return new PermissionDTO(permission.getPermission(), permission.isRemovable());
    }
}
