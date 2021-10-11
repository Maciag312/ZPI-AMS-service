package com.zpi.api.manager;

import com.zpi.api.manager.dto.ManagerDTO;
import com.zpi.domain.manager.ManagerAccountManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manager")
public class ManagerController {
    private final ManagerAccountManager accountManager;

    @PostMapping("/signin")
    public String signIn(@RequestBody ManagerDTO signInDTO) {
        return accountManager.signIn(signInDTO.getUsername(), signInDTO.getPassword());
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody ManagerDTO signUpDTO) {

    }

}
