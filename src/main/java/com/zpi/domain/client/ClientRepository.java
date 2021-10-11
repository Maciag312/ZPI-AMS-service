package com.zpi.domain.client;

import com.zpi.domain.common.EntityRepository;

import java.util.Optional;

public interface ClientRepository extends EntityRepository<String, Client> {
    Optional<Client> findByName(String client_name);
}
