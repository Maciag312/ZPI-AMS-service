package com.zpi.infrastructure.persistance.permission;

import com.zpi.domain.permission.Permission;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PermissionTuple {
    private final String id;

    public static PermissionTuple fromDomain(Permission permission) {
        return new PermissionTuple(permission.getPermission());
    }

    public Permission toDomain() {
        return new Permission(id);
    }
}
