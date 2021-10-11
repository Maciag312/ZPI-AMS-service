package com.zpi.domain.manager;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository {
    void save(Manager manager);
    Optional<Manager> findByUsername(String username);
    List<Manager> findAllByRole(Role role);
    void clear();
}
