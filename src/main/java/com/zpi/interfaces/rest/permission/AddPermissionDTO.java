package com.zpi.interfaces.rest.permission;

import com.zpi.domain.permission.Permission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddPermissionDTO {
    private String permission;

    public static AddPermissionDTO fromDomain(Permission permission) {
        return new AddPermissionDTO(permission.getPermission());
    }

}
