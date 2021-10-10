package com.zpi.infrastructure.common;

import com.zpi.domain.common.EntityRepository;

import java.util.HashMap;
import java.util.Optional;

public abstract class InMemoryRepository<KeyType, EntityType> implements EntityRepository<KeyType, EntityType> {

    protected HashMap<KeyType, EntityTuple<EntityType>> repository = new HashMap<>();

    public void save(KeyType key, EntityType entity) {
        repository.put(key, fromDomain(entity));
    }

    public Optional<EntityType> findByKey(KeyType key) {
        return Optional.ofNullable(repository.get(key))
                .map(EntityTuple::toDomain);
    }

    public void clear(){
        repository.clear();
    }

    public abstract EntityTuple<EntityType> fromDomain(EntityType entity);

}
