package com.zpi.api.organization.user;

import com.zpi.api.common.dto.UserDTO;
import com.zpi.domain.organization.OrganizationService;
import com.zpi.domain.organization.user.UserManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/organization/{name}/user")
public class UserController {
    private final UserManager userService;
    private final OrganizationService organizationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO, @PathVariable String name) {
        if (!organizationService.exists(name)) {
            throw new IllegalArgumentException("Organization with such name doesn't exists");
        }
        var user = userDTO.toHashedDomain();
        user.setOrganization(name);
        if (userService.createUser(user)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
