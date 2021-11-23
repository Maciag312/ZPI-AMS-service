package com.zpi.interfaces.rest.user;

import com.google.zxing.WriterException;
import com.zpi.interfaces.rest.common.dto.UserLoginDTO;
import com.zpi.domain.user.UserManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;
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
            return status(CREATED).build();
        }
        return status(CONFLICT).build();
    }

    @PutMapping("/attributes")
    public ResponseEntity<?> updateAttributes(@Valid @RequestBody UpdateAttributesDTO attributesDTO) {
        try {
            manager.updateAttributes(attributesDTO.getUserEmail(), attributesDTO.getAttributes());
            return ok().build();
        } catch (IllegalArgumentException ex) {
            return status(CONFLICT).build();
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody UserLoginDTO userLoginDTO) {
        var user = userLoginDTO.toDomain();
        try {
            if (manager.authenticate(user)) {
                return ok().build();
            }
            return status(UNAUTHORIZED).body("Password is not accepted");
        } catch (IllegalArgumentException ex) {
            return status(UNAUTHORIZED).body(ex);
        }
    }

    @PostMapping("/otp/generate")
    public ResponseEntity<?> oneTimePasswordAuthenticate(@RequestBody UserEmailDTO email) {
        try {
            manager.generateQrCodePassword(email.email);
            return ok().build();
        } catch (IllegalArgumentException ex) {
            return status(UNAUTHORIZED).body(ex);
        } catch (IOException | WriterException | MessagingException ex) {
            return internalServerError().body(ex);
        }
    }

    @GetMapping
    public List<UserDTO> getAll() {
        return manager.getAll().stream()
                .map(UserDTO::fromDomain)
                .collect(Collectors.toList());
    }

    @PostMapping("/info")
    public ResponseEntity<UserInfoDTO> getUserInfo(@RequestBody UserEmailDTO getUserInfo) {
        Optional<UserInfoDTO> userInfo =  manager.getUserInfo(getUserInfo.email).map(UserInfoDTO::fromDomain);
        if (userInfo.isEmpty()) {
            return badRequest().build();
        }
        return ok(userInfo.get());
    }

    @PostMapping("/activate")
    public ResponseEntity<?> activate(@RequestBody UserEmailDTO getUserInfo) {
        try {
            manager.activate(getUserInfo.email);
            return ok().build();
        } catch (IllegalArgumentException ex) {
            return badRequest().build();
        }
    }

    @PostMapping("/deactivate")
    public ResponseEntity<?> deactivate(@RequestBody UserEmailDTO getUserInfo) {
        try {
            manager.deactivate(getUserInfo.email);
            return ok().build();
        } catch (IllegalArgumentException ex) {
            return badRequest().build();
        }
    }

    @PostMapping("/password/renew")
    public ResponseEntity<?> renewPassword(@RequestBody UserEmailDTO getUserInfo) {
        try {
            manager.renewPassword(getUserInfo.email);
            return ok().build();
        } catch (IllegalArgumentException ex) {
            return badRequest().build();
        }
    }

    @PostMapping("/assign-role")
    public ResponseEntity assignToRole(@RequestBody AssignRoleToUserDTO assignRoleToUserDTO) {
        try {
            manager.assignRole(assignRoleToUserDTO.email, assignRoleToUserDTO.role);
            return ok().build();
        } catch (IllegalArgumentException ex) {
            return badRequest().build();
        }
    }

    @DeleteMapping("/remove-role")
    public ResponseEntity removeFromRole(@RequestBody AssignRoleToUserDTO assignRoleToUserDTO) {
        try {
            manager.removeRoleFromUser(assignRoleToUserDTO.email, assignRoleToUserDTO.role);
            return noContent().build();
        } catch (IllegalArgumentException ex) {
            return badRequest().build();
        }
    }
}
