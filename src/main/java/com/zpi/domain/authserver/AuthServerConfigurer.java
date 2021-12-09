package com.zpi.domain.authserver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthServerConfigurer {

    private final AuthServerRepository repository;

    public void createOrUpdate(AuthServerConfiguration configuration){
        repository.createOrUpdate(configuration);
    }
    public AuthServerConfiguration get(){
        return repository.get();
    }

}
