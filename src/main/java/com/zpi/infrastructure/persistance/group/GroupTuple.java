package com.zpi.infrastructure.persistance.group;

import com.zpi.domain.group.Group;
import com.zpi.domain.group.rule.Rule;
import com.zpi.infrastructure.persistance.permission.PermissionTuple;
import lombok.Value;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Value
public class GroupTuple {
    String name;
    String color;
    Set<PermissionTuple> permissions;

    public static GroupTuple fromDomain(Group group) {
        return new GroupTuple(group.getName(), group.getColor(), group.getPermissions().stream().map(PermissionTuple::fromDomain).collect(Collectors.toSet()));
    }

    public static Group toDomain(GroupTuple groupTuple) {
        var permissions = groupTuple.getPermissions().stream()
                .map(PermissionTuple::toDomain)
                .collect(Collectors.toSet());
        return new Group(groupTuple.getName(), groupTuple.getColor(), permissions, new Rule(List.of(List.of())));
    }
}
