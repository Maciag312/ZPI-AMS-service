package com.zpi.infrastructure.organization.client;

import com.zpi.domain.organization.client.Client;
import com.zpi.domain.organization.client.ClientRepository;
import com.zpi.infrastructure.common.EntityTuple;
import com.zpi.infrastructure.common.InMemoryRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.function.Predicate;


@Repository
class InMemoryClientRepository extends InMemoryRepository<String, Client> implements ClientRepository {
    @Override
    public EntityTuple<Client> fromDomain(Client client) {
        return new ClientTuple(client);
    }


    @Override
    public Optional<Client> findByNameAndOrganizationName(String client_name, String organization_name) {
        Predicate<Client> isInGivenOrganization = client -> client.getOrganizationName().equals(organization_name);
        return findByKey(client_name).filter(isInGivenOrganization);
    }

}
