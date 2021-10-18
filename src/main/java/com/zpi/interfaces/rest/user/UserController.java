package com.zpi.interfaces.rest.user;

import com.zpi.interfaces.rest.common.dto.UserDTO;
import com.zpi.domain.user.UserManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserManager userService;

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO) {
        var user = userDTO.toHashedDomain();
        if (userService.createUser(user)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
