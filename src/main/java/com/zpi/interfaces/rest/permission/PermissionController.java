package com.zpi.interfaces.rest.permission;

import com.zpi.domain.permission.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping
    public ResponseEntity<String> add(@RequestBody AddPermissionDTO permissionDTO) {
        try {
            permissionService.add(permissionDTO.getPermission());
            return ResponseEntity.created(URI.create("/api/permissions")).build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping
    public Set<PermissionDTO> getAll(){
       return permissionService.getAll().stream()
               .map(PermissionDTO::fromDomain)
               .collect(Collectors.toSet());
    }

    @DeleteMapping
    public ResponseEntity remove(String permission){
        permissionService.remove(permission);
        return ResponseEntity.noContent().build();
    }


}
