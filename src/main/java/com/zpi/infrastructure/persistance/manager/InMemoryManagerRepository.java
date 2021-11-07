package com.zpi.infrastructure.persistance.manager;


import com.zpi.domain.manager.Manager;
import com.zpi.domain.manager.ManagerRepository;
import com.zpi.domain.manager.Role;
import com.zpi.infrastructure.common.EntityTuple;
import com.zpi.infrastructure.common.InMemoryRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class InMemoryManagerRepository extends InMemoryRepository<String, Manager> implements ManagerRepository {

    Map<String, Manager> managers = new HashMap<>();

    @Override
    public void save(Manager manager) {
        managers.put(manager.getUsername(), manager);
    }

    @Override
    public Optional<Manager> findByUsername(String username) {
        return managers.entrySet().stream()
                .filter( entry->entry.getKey().equals(username))
                .findFirst()
                .map(Map.Entry::getValue);
    }

    @Override
    public List<Manager> findAllByRole(Role role) {
        return managers.values().stream()
                .filter(m->m.getRoles().contains(role))
                .collect(Collectors.toList());
    }

    @Override
    public EntityTuple<Manager> fromDomain(Manager manager) {
        return new ManagerTuple(manager.getUsername(), manager.getPassword(), manager.getRoles());
    }
}
