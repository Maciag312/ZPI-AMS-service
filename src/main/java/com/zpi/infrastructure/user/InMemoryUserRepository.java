package com.zpi.infrastructure.user;

import com.zpi.domain.user.User;
import com.zpi.domain.user.UserRepository;
import com.zpi.infrastructure.common.EntityTuple;
import com.zpi.infrastructure.common.InMemoryRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryUserRepository extends InMemoryRepository<String, User> implements UserRepository {

    @Override
    public EntityTuple<User> fromDomain(User user) {
        return new UserTuple(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(repository.get(username).toDomain());
    }
}
