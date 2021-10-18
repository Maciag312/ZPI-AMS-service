package com.zpi.domain.manager;

import com.zpi.infrastructure.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.zpi.domain.manager.Role.*;

@Component
@RequiredArgsConstructor
public class ManagerAccountMangerImpl implements ManagerAccountManager {

    private final ManagerRepository repository;
    private final JwtTokenProvider tokenProvider;

    @Override
    public String signIn(String username, String password) throws IllegalArgumentException {
        IllegalArgumentException exception =  new IllegalArgumentException("Manager is not found or provided credentials are wrong");
        var manager = repository.findByUsername(username)
                .orElseThrow(() -> exception);
        if (!manager.getPassword().equals(password)) {
            throw exception;
        }
        return tokenProvider.createToken(manager.getUsername(), manager.getRoles());
    }

    @Override
    public void createAccount(String username, String password) throws IllegalArgumentException {
        if(repository.findByUsername(username).isPresent()){
            throw new IllegalArgumentException("Account with such name already exists for this organization");
        }
        repository.save(new Manager(username, password, List.of(MANAGER)));
    }

    @Override
    public void changePassword(String username, String password) {
        Optional<Manager> manager = repository.findByUsername(username);
        if(manager.isEmpty() || !manager.get().getPassword().equals(password)){
            throw new IllegalArgumentException("Wrong credentials.");
        }
        manager.get().setPassword(password);
    }
}
