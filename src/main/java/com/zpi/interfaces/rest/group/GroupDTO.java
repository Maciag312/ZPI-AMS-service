package com.zpi.interfaces.rest.group;

import com.zpi.domain.group.Group;
import com.zpi.domain.group.rule.Rule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
class GroupDTO {
    private String group;
    private String color;
    private Set<AssignPermissionDTO> permissions;
    private RuleDTO rule;

    public static GroupDTO fromDomain(Group group) {
        var permissions = group.getPermissions().stream().map(AssignPermissionDTO::fromDomain).collect(Collectors.toSet());
        return new GroupDTO(group.getName(), group.getColor(), permissions, RuleDTO.fromDomain(group.getRule()));
    }
}
