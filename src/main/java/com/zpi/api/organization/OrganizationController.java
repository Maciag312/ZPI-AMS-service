package com.zpi.api.organization;

import com.zpi.domain.organization.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping("/register/{name}")
    public ResponseEntity<?> register(@PathVariable String name) {
        organizationService.register(name);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
