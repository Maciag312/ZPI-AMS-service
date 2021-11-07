package com.zpi.interfaces.rest.user;

import com.zpi.interfaces.rest.common.dto.UserLoginDTO;
import com.zpi.domain.user.UserManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.*;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserManager manager;

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO) {
        var user = userDTO.toDomain();
        if (manager.createUser(user)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody UserLoginDTO userLoginDTO) {
        var user = userLoginDTO.toDomain();
        if (manager.authenticate(user)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping
    public List<UserDTO> getAll() {
        return manager.getAll().stream()
                .map(UserDTO::fromDomain)
                .collect(Collectors.toList());
    }

    @PostMapping("/info")
    public ResponseEntity<UserInfoDTO> getUserInfo(@RequestBody GetUserInfoDTO getUserInfo) {
        Optional<UserInfoDTO> userInfo =  manager.getUserInfo(getUserInfo.email).map(UserInfoDTO::fromDomain);
        if (userInfo.isEmpty()) {
            return badRequest().build();
        }
        return ok(userInfo.get());
    }

    @PostMapping("/assign-role")
    public ResponseEntity assignRole(@RequestBody AssignRoleToUserDTO assignRoleToUserDTO) {
        try {
            manager.assignRole(assignRoleToUserDTO.email, assignRoleToUserDTO.role);
            return ok().build();
        } catch (IllegalArgumentException ex) {
            return badRequest().build();
        }
    }

    @DeleteMapping("/remove-role")
    public ResponseEntity removeRole(@RequestBody AssignRoleToUserDTO assignRoleToUserDTO) {
        try {
            manager.removeRoleFromUser(assignRoleToUserDTO.email, assignRoleToUserDTO.role);
            return noContent().build();
        } catch (IllegalArgumentException ex) {
            return badRequest().build();
        }
    }
}
