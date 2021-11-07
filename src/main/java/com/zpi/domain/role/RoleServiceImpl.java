package com.zpi.domain.role;

import com.zpi.domain.permission.Permission;
import com.zpi.domain.permission.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public void add(String role, Set<Permission> permissions) {
        roleRepository.save(new Role(role, permissions));
    }

    @Override
    public Optional<Role> find(String role) {
        return roleRepository.find(role);
    }

    @Override
    public void assignPermissionToRole(String role, String permission) throws IllegalArgumentException{
        var roleOptional  = roleRepository.find(role);
        if(roleOptional.isEmpty()) {
            throw new IllegalArgumentException(format("Could not find role=[%s]", role));
        }
        var permissionOptimal = permissionRepository.find(permission);
        if(permissionOptimal.isEmpty()) {
            throw new IllegalArgumentException(format("Permission=[%s] is not registred", permission));
        }
        roleOptional.get().assign(permissionOptimal.get());
        roleRepository.save(roleOptional.get());
    }

    @Override
    public void remove(String permission) {
        roleRepository.remove(permission);
    }

    @Override
    public Set<Role> getAll() {
        return roleRepository.getAll();
    }

    @Override
    public void removePermissionFromRole(String name, String permission) {
        var roleOptional  = roleRepository.find(name);
        if(roleOptional.isEmpty()) {
            throw new IllegalArgumentException(format("Could not find role=[%s]", name));
        }
        var permissionOptimal = permissionRepository.find(permission);
        if(permissionOptimal.isEmpty()) {
            throw new IllegalArgumentException(format("Permission=[%s] is not registred", permission));
        }
        roleOptional.get().remove(permissionOptimal.get());
        roleRepository.save(roleOptional.get());
    }

}
