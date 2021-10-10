package com.zpi.infrastructure.organization.manager;


import com.zpi.domain.organization.manager.Manager;
import com.zpi.domain.organization.manager.ManagerRepository;
import com.zpi.domain.organization.manager.Role;
import com.zpi.infrastructure.common.CompositeKey;
import com.zpi.infrastructure.common.EntityTuple;
import com.zpi.infrastructure.common.InMemoryRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class InMemoryManagerRepository extends InMemoryRepository<CompositeKey<String, String>, Manager> implements ManagerRepository {

    Map<CompositeKey<String/*username*/, String /*organization name*/>, Manager> managers = new HashMap<>();

    @Override
    public void save(Manager manager) {
        managers.put(new CompositeKey<>(manager.getUsername(), manager.getOrganizationName()), manager);
    }


    @Override
    public Optional<Manager> findByOrganizationNameAndUsername(String organizationName, String username) {
        return Optional.ofNullable(managers.get(new CompositeKey<>(username, organizationName)));
    }

    @Override
    public Optional<Manager> findByUsername(String username) {
        return managers.entrySet().stream()
                .filter( entry->entry.getKey().left.equals(username))
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
        return new ManagerTuple(manager.getUsername(), manager.getPassword(), manager.getRoles(), manager.getOrganizationName());
    }
}
