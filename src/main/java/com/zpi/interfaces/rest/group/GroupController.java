package com.zpi.interfaces.rest.group;

import com.zpi.domain.group.GroupService;
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
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<String> add(@RequestBody AddGroupDTO groupDTO) {
        try {
            groupService.add(groupDTO.getGroup(), groupDTO.getColor(), Collections.emptySet());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping
    public Set<GroupDTO> getAll(){
       return groupService.getAll().stream()
               .map(GroupDTO::fromDomain)
               .collect(Collectors.toSet());
    }

    @DeleteMapping("/{name}")
    public ResponseEntity remove(@PathVariable String name){
        groupService.remove(name);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{name}/assign-permission")
    public ResponseEntity assignPermission(@PathVariable String name, @RequestBody AssignPermissionDTO permissionDTO) {
        try {
            groupService.assignPermissionToGroup(name, permissionDTO.getPermission());
            return accepted().build();
        } catch (IllegalArgumentException reason) {
            return badRequest()
                    .body(reason.getMessage());
        }
    }

    @PostMapping("/{name}/rule")
    public ResponseEntity addRule(@PathVariable String name, @RequestBody RuleDTO ruleDTO) {
        try {
            groupService.addRule(name, ruleDTO.toDomain());
            return accepted().build();
        } catch (IllegalArgumentException reason) {
            return badRequest()
                    .body(reason.getMessage());
        }
    }

    @GetMapping("/{name}/rule")
    public ResponseEntity getRules(@PathVariable String name) {
        try {
            return ok(RuleDTO.fromDomain(groupService.getRule(name)));
        } catch (IllegalArgumentException reason) {
            return badRequest().body(reason.getMessage());
        }
    }

    @DeleteMapping("/{name}/remove-permission")
    public ResponseEntity removePermission(@PathVariable String name, @RequestBody AssignPermissionDTO permissionDTO) {
        try {
            groupService.removePermissionFromGroup(name, permissionDTO.getPermission());
            return noContent().build();
        } catch (IllegalArgumentException reason) {
            return badRequest()
                    .body(reason.getMessage());
        }
    }

}
