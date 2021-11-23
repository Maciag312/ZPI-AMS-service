package com.zpi.infrastructure.persistance.group;

import com.zpi.domain.group.Group;
import com.zpi.domain.group.GroupRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class InMemoryGroupRepository implements GroupRepository {

    HashMap<String, GroupTuple> groups = new HashMap<>();

    @Override
    public void save(Group group) {
        groups.put(group.getName(), GroupTuple.fromDomain(group));
    }

    @Override
    public void remove(String role) {
        groups.remove(role);
    }

    @Override
    public Optional<Group> find(String role) {
        return Optional.ofNullable(groups.get(role)).map(GroupTuple::toDomain);
    }

    @Override
    public Set<Group> getAll() {
        return groups.values().stream()
                .map(GroupTuple::toDomain)
                .collect(Collectors.toSet());
    }
}
