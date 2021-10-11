package com.zpi.infrastructure.client;

import com.zpi.domain.client.Client;
import com.zpi.domain.client.ClientRepository;
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
    public Optional<Client> findByName(String client_name) {
        return Optional.ofNullable(repository.get(client_name))
                .map(EntityTuple::toDomain);
    }
}
