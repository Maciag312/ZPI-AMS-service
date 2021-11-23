package com.zpi.domain.group;

import com.zpi.domain.group.rule.Rule;
import com.zpi.domain.permission.Permission;
import lombok.Value;

import java.util.Set;

@Value
public class Group {
    String name;
    String color;
    Set<Permission> permissions;
    Rule rule;

    public void assign(Permission permission) {
        permissions.add(permission);
    }

    public void remove(Permission permission) {
        permissions.remove(permission);
    }
}
