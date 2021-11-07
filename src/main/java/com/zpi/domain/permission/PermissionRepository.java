package com.zpi.domain.permission;

import java.util.Optional;
import java.util.Set;

public interface PermissionRepository {
    void save(Permission permission);
    void remove(String permission);
    Set<Permission> getAll();
    Optional<Permission> find(String permission);
}
