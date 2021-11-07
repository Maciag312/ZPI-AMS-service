package com.zpi.infrastructure.persistance.role;

import com.zpi.domain.role.Role;
import com.zpi.domain.role.RoleRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class InMemoryRoleRepository implements RoleRepository {

    HashMap<String, RoleTuple> roles = new HashMap<>();

    @Override
    public void save(Role role) {
        roles.put(role.getName(), RoleTuple.fromDomain(role));
    }

    @Override
    public void remove(String role) {
        roles.remove(role);
    }

    @Override
    public Optional<Role> find(String role) {
        return Optional.ofNullable(roles.get(role)).map(RoleTuple::toDomain);
    }

    @Override
    public Set<Role> getAll() {
        return roles.values().stream()
                .map(RoleTuple::toDomain)
                .collect(Collectors.toSet());
    }
}
