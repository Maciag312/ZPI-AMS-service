package com.zpi.infrastructure.persistance.user;

import com.zpi.domain.user.User;
import com.zpi.domain.user.UserRepository;
import com.zpi.infrastructure.common.EntityTuple;
import com.zpi.infrastructure.common.InMemoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepository extends InMemoryRepository<String, User> implements UserRepository {

    @Override
    public EntityTuple<User> fromDomain(User user) {
        return new UserTuple(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        var userTuple = repository.get(email);
        if(userTuple != null) {
            return Optional.of(userTuple.toDomain());
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return repository.values()
                .stream()
                .map(EntityTuple::toDomain)
                .collect(Collectors.toList());
    }
}
