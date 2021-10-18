package com.zpi.domain.user;

import com.zpi.domain.common.EntityRepository;

import java.util.Optional;

public interface UserRepository extends EntityRepository<String, User> {
    Optional<User> findByUsername(String username);
}
