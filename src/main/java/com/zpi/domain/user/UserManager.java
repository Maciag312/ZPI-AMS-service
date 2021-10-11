package com.zpi.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserManager {
    private final UserRepository userRepository;

    public boolean createUser(User user) {
        var login = user.getLogin();

        if (userRepository.findByKey(login).isPresent()) {
            return false;
        }

        userRepository.save(login, user);
        return true;
    }
}
