package com.zpi.domain.role;

import com.zpi.domain.permission.Permission;

import java.util.Optional;
import java.util.Set;

public interface RoleService {
    void add(String role, String color, Set<Permission> permissions);
    Optional<Role> find(String role);
    void assignPermissionToRole(String role, String permission);
    void remove(String role);
    Set<Role> getAll();
    void removePermissionFromRole(String name, String permission);
}
