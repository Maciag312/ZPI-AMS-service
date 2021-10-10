package com.zpi.domain.common;

import java.util.Optional;

public interface EntityRepository<KeyType, EntityType> {
    void save(KeyType key, EntityType item);
    Optional<EntityType> findByKey(KeyType key);
    void clear();
}
