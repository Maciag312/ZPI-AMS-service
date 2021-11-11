package com.zpi.domain.user;

import java.util.List;
import java.util.Optional;

public interface OneTimePasswordRepository {
    void save(OneTimePassword oneTimePassword);
    List<OneTimePassword> findBy(String email);
}
