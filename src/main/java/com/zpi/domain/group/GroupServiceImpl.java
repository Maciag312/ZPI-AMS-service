package com.zpi.domain.group;

import com.zpi.domain.group.rule.Rule;
import com.zpi.domain.permission.Permission;
import com.zpi.domain.permission.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public void add(String group, String color, Set<Permission> permissions) {
        groupRepository.save(new Group(group, color, permissions, new Rule(List.of(List.of()))));
    }

    @Override
    public Optional<Group> find(String group) {
        return groupRepository.find(group);
    }

    @Override
    public void assignPermissionToGroup(String group, String permission) throws IllegalArgumentException {
        var groupOptional  = groupRepository.find(group);
        if(groupOptional.isEmpty()) {
            throw new IllegalArgumentException(format("Could not find group=[%s]", group));
        }
        var permissionOptimal = permissionRepository.find(permission);
        if(permissionOptimal.isEmpty()) {
            throw new IllegalArgumentException(format("Permission=[%s] is not registred", permission));
        }
        groupOptional.get().assign(permissionOptimal.get());
        groupRepository.save(groupOptional.get());
    }

    @Override
    public void remove(String permission) {
        groupRepository.remove(permission);
    }

    @Override
    public Set<Group> getAll() {
        return groupRepository.getAll();
    }

    @Override
    public void removePermissionFromGroup(String name, String permission) {
        var groupOptional  = groupRepository.find(name);
        if(groupOptional.isEmpty()) {
            throw new IllegalArgumentException(format("Could not find group=[%s]", name));
        }
        var permissionOptimal = permissionRepository.find(permission);
        if(permissionOptimal.isEmpty()) {
            throw new IllegalArgumentException(format("Permission=[%s] is not registred", permission));
        }
        groupOptional.get().remove(permissionOptimal.get());
        groupRepository.save(groupOptional.get());
    }

}
