package com.zpi.domain.group;

import java.util.Optional;
import java.util.Set;

public interface GroupRepository {
    void save(Group group);
    void remove(String group);
    Optional<Group> find(String group);
    Set<Group> getAll();
}
