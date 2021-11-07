package com.zpi.domain.role;

import com.zpi.domain.permission.Permission;
import lombok.Value;

import java.util.Set;

@Value
public class Role {
    String name;
    Set<Permission> permissions;

    public void assign(Permission permission) {
        permissions.add(permission);
    }

    public void remove(Permission permission) {
        permissions.remove(permission);
    }
}
