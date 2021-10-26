package com.zpi.domain.client;

import com.zpi.domain.common.EntityRepository;

import java.util.Optional;
import java.util.Set;

public interface ClientRepository extends EntityRepository<String, Client> {
    Optional<Client> findByName(String client_name);
    Set<Client> findAll();
    void deleteById(String id);
    void removeUriFromClient(String uri, String id);
}
