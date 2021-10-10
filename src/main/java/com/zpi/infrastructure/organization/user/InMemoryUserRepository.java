package com.zpi.infrastructure.organization.user;

import com.zpi.domain.organization.user.User;
import com.zpi.domain.organization.user.UserRepository;
import com.zpi.infrastructure.common.EntityTuple;
import com.zpi.infrastructure.common.InMemoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserRepository extends InMemoryRepository<String, User> implements UserRepository {

    @Override
    public EntityTuple<User> fromDomain(User user) {
        return new UserTuple(user);
    }
}
