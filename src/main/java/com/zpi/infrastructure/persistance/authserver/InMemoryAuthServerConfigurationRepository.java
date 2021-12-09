package com.zpi.infrastructure.persistance.authserver;


import com.zpi.domain.authserver.AuthServerConfiguration;
import com.zpi.domain.authserver.AuthServerRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryAuthServerConfigurationRepository implements AuthServerRepository {
    AuthServerConfigurationTuple authServerConfiguration = new AuthServerConfigurationTuple(3600000, "DJASFDNUS812DAMNXMANSDHQHW83183JD18JJ1HFG8JXJ12JSH1XCHBUJ28X2JH12J182XJH1F3H1JS81G7RESHD13H71");


    @Override
    public void createOrUpdate(AuthServerConfiguration configuration) {
        authServerConfiguration = AuthServerConfigurationTuple.fromDomain(configuration);
    }

    @Override
    public AuthServerConfiguration get() {
        return authServerConfiguration.toDomain();
    }
}
