package com.zpi.infrastructure.security;

import com.zpi.domain.manager.Manager;
import com.zpi.domain.manager.ManagerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MyUserDetails implements UserDetailsService {

    private final ManagerRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<Manager> user = repository.findByUsername(username);

        if (user.isPresent()) {
            return org.springframework.security.core.userdetails.User
                    .withUsername(username)
                    .password(user.get().getPassword())
                    .authorities(user.get().getRoles())
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .build();
        }
        throw new UsernameNotFoundException("Manager '" + username + "' not found");
    }

}
