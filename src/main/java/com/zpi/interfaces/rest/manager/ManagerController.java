package com.zpi.interfaces.rest.manager;

import com.zpi.interfaces.rest.manager.dto.SignInDTO;
import com.zpi.interfaces.rest.manager.dto.SignUpDTO;
import com.zpi.domain.manager.ManagerAccountManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manager")
public class ManagerController {

    private final ManagerAccountManager accountManager;

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody SignInDTO signInDTO) {
        try {
            return ResponseEntity.ok(accountManager.signIn(signInDTO.getUsername(), signInDTO.getPassword()));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody SignUpDTO signUpDTO) {
        try {
            accountManager.createAccount(signUpDTO.getUsername(), signUpDTO.getPassword());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
