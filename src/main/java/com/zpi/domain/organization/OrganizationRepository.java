package com.zpi.domain.organization;

import java.util.Optional;

public interface OrganizationRepository {
    void save(Organization organization);
    Optional<Organization> findByName(String name);
    void clear();
}
