package com.zpi.domain.permission;

import java.util.Set;

public interface PermissionService {
    void add(String permission);
    void remove(String permission);
    Set<Permission> getAll();
}
