package com.zpi.domain.user;

import com.zpi.domain.role.Role;
import com.zpi.domain.role.RoleService;
import com.zpi.infrastructure.mail.EmailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class UserManager {
    private final UserRepository userRepository;
    private final RoleService roleService;

    private final EmailServiceImpl emailService;

    public boolean createUser(User user) {
        var email = user.getEmail();
        if (userRepository.findByKey(email).isPresent()) {
            return false;
        }
        renewPassword(user);
        userRepository.save(email, user);
        return true;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    private void renewPassword(User user) {
        String message = "Dear Maddam/Sir \n AMS system has generated new password for you: " + user.renewPassword(8);
        emailService.sendSimpleMessage(user.getEmail(), "AMS generated new password for you", message);
    }

    public boolean authenticate(User user) {
        var userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isEmpty()) {
            return false;
        }
        return userOptional.get().getPassword().equals(user.getPassword());
    }

    public Optional<UserInfo> getUserInfo(String email) {
        var optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()) {
            return Optional.empty();
        }

        var userPermissions =   optionalUser.get().getRoles().stream()
                .map(Role::getPermissions)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        return Optional.of(new UserInfo(email, userPermissions, optionalUser.get().getRoles()));
    }

    public void assignRole(String userEmail, String role){
        var user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException(format("User with email=[%s] doesnt exists", userEmail)));

        var domainRole = roleService.find(role)
                .orElseThrow(() -> new IllegalArgumentException(format("Role with name=[%s] doesnt exists", role)));

        user.roles.add(domainRole);
        userRepository.save(user.getEmail(), user);
    }

    public void removeRoleFromUser(String userEmail, String role){
        var user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException(format("User with email=[%s] doesnt exists", userEmail)));

        var domainRole = roleService.find(role)
                .orElseThrow(() -> new IllegalArgumentException(format("Role with name=[%s] doesnt exists", role)));

        user.roles.remove(domainRole);
        userRepository.save(user.getEmail(), user);
    }
}
