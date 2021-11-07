package com.zpi.infrastructure.persistance.role;

import com.zpi.domain.role.Role;
import com.zpi.infrastructure.persistance.permission.PermissionTuple;
import lombok.Value;

import java.util.Set;
import java.util.stream.Collectors;

@Value
public class RoleTuple {
    String name;
    Set<PermissionTuple> permissions;

    public static RoleTuple fromDomain(Role role) {
        return new RoleTuple(role.getName(), role.getPermissions().stream().map(PermissionTuple::fromDomain).collect(Collectors.toSet()));
    }

    public static Role toDomain(RoleTuple roleTuple) {
        var permissions = roleTuple.getPermissions().stream()
                .map(PermissionTuple::toDomain)
                .collect(Collectors.toSet());
        return new Role(roleTuple.getName(), permissions);
    }
}
