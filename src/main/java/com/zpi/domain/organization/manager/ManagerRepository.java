package com.zpi.domain.organization.manager;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository {
    void save(Manager manager);
    Optional<Manager> findByOrganizationNameAndUsername(String organizationName, String username);
    Optional<Manager> findByUsername(String username);
    List<Manager> findAllByRole(Role role);
    void clear();
}
