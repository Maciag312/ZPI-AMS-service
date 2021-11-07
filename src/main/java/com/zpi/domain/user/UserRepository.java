package com.zpi.domain.user;

import com.zpi.domain.common.EntityRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends EntityRepository<String, User> {
    Optional<User> findByEmail(String email);
    List<User> findAll();
}
