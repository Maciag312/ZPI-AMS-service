package com.zpi.domain.organization.client;

import com.zpi.domain.common.EntityRepository;

import java.util.Optional;

public interface ClientRepository extends EntityRepository<String, Client> {
    Optional<Client> findByNameAndOrganizationName(String client_name, String organization_name);
}
