package com.zpi.infrastructure.organization;

import com.zpi.domain.organization.Organization;
import com.zpi.domain.organization.OrganizationRepository;
import com.zpi.infrastructure.common.EntityTuple;
import com.zpi.infrastructure.common.InMemoryRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryOrganizationRepository extends InMemoryRepository<String, Organization> implements OrganizationRepository {
    @Override
    public void save(Organization organization) {
        save(organization.getName(), organization);
    }
    @Override
    public Optional<Organization> findByName(String name) {
        return findByKey(name);
    }

    @Override
    public EntityTuple<Organization> fromDomain(Organization entity) {
        return new OrganizationTuple(entity.getName());
    }
}
