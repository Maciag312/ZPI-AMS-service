package com.zpi.domain.group;

import com.zpi.domain.permission.Permission;

import java.util.Optional;
import java.util.Set;

public interface GroupService {
    void add(String role, String color, Set<Permission> permissions);
    Optional<Group> find(String role);
    void remove(String role);
    Set<Group> getAll();
    void removePermissionFromGroup(String name, String permission);
    void assignPermissionToGroup(String group, String permission);
}
