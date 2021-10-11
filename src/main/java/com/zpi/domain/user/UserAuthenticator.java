package com.zpi.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserAuthenticator {
    private final UserRepository userRepository;

    public boolean isAuthenticated(User user) {
        var login = user.getLogin();

        var found = userRepository.findByKey(login);

        if (found.isEmpty()) {
            return false;
        }

        return found.get().getPassword().equals(user.getPassword());
    }
}
