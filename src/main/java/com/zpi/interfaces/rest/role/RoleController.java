package com.zpi.interfaces.rest.role;

import com.zpi.domain.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<String> add(@RequestBody AddRoleDTO roleDTO) {
        try {
            roleService.add(roleDTO.getRole(), roleDTO.getColor(), Collections.emptySet());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping
    public Set<RoleDTO> getAll(){
       return roleService.getAll().stream()
               .map(RoleDTO::fromDomain)
               .collect(Collectors.toSet());
    }

    @DeleteMapping
    public ResponseEntity remove(String permission){
        roleService.remove(permission);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{name}/assign-permission")
    public ResponseEntity assignPermission(@PathVariable String name, @RequestBody AssignPermissionDTO permissionDTO) {
        try {
            roleService.assignPermissionToRole(name, permissionDTO.getPermission());
            return accepted().build();
        } catch (IllegalArgumentException reason) {
            return badRequest()
                    .body(reason.getMessage());
        }
    }

    @DeleteMapping("/{name}/assign-permission")
    public ResponseEntity removePermission(@PathVariable String name, @RequestBody AssignPermissionDTO permissionDTO) {
        try {
            roleService.removePermissionFromRole(name, permissionDTO.getPermission());
            return noContent().build();
        } catch (IllegalArgumentException reason) {
            return badRequest()
                    .body(reason.getMessage());
        }
    }

}
