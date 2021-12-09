package com.zpi.domain.authserver;

public interface AuthServerRepository {
    void createOrUpdate(AuthServerConfiguration configuration);
    AuthServerConfiguration get();
}
