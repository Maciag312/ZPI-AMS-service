package com.zpi.api.organization.manager;

import com.zpi.api.organization.manager.dto.ManagerDTO;
import com.zpi.domain.organization.OrganizationService;
import com.zpi.domain.organization.manager.ManagerAccountManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/organization/{name}/manager")
public class ManagerController {
    private final ManagerAccountManager accountManager;
    private final OrganizationService organizationService;

    @PostMapping("/signin")
    public String signIn(@RequestBody ManagerDTO signInDTO, @PathVariable String name) {
        if (!organizationService.exists(name)) {
            throw new IllegalArgumentException("Organization with such name doesn't exists");
        }
        return accountManager.signIn(name, signInDTO.getUsername(),
                signInDTO.getPassword());
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody ManagerDTO signUpDTO, @PathVariable String name) {

    }

}
