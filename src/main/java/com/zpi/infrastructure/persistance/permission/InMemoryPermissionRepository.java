package com.zpi.infrastructure.persistance.permission;

import com.zpi.domain.permission.Permission;
import com.zpi.domain.permission.PermissionRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class InMemoryPermissionRepository implements PermissionRepository {

    HashMap<String, PermissionTuple> permissions = new HashMap<>();

    @Override
    public void save(Permission permission) {
        permissions.put(permission.getPermission(), PermissionTuple.fromDomain(permission));
    }

    @Override
    public void remove(String permission) {
        permissions.remove(permission);
    }

    @Override
    public Set<Permission> getAll() {
        return permissions.values().stream()
                .map(PermissionTuple::toDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<Permission> find(String permission) {
        return Optional.ofNullable(permissions.get(permission))
                .map(PermissionTuple::toDomain);
    }
}
