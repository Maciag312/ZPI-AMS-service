package com.zpi.domain.role;

import java.util.Optional;
import java.util.Set;

public interface RoleRepository {
    void save(Role role);
    void remove(String role);
    Optional<Role> find(String role);
    Set<Role> getAll();
}
