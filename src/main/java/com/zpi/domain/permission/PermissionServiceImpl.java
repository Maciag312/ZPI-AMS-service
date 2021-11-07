package com.zpi.domain.permission;

import com.zpi.domain.role.Role;
import com.zpi.domain.role.RoleRepository;
import com.zpi.domain.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository repository;
    private final RoleService roleService;

    @Override
    public void add(String permission) {
        repository.save(new Permission(permission));
    }

    @Override
    public void remove(String permission) {
        repository.remove(permission);
    }

    @Override
    public Set<Permission> getAll() {
        Set<Permission> permissions = roleService.getAll().stream()
                .map(Role::getPermissions)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        var perms = repository.getAll();
        perms.forEach(permission -> permission.isRemovable = permissions.contains(permission));
        return perms;
    }

}
