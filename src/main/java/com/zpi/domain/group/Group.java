package com.zpi.domain.group;

import com.zpi.domain.group.rule.Rule;
import com.zpi.domain.permission.Permission;
import com.zpi.domain.user.User;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class Group {
    private final String name;
    private final String color;
    private final Set<Permission> permissions;
    Rule rule = new Rule("", List.of());

    public void assign(Permission permission) {
        permissions.add(permission);
    }
    public void remove(Permission permission) {
        permissions.remove(permission);
    }

    public boolean belongs(User user) {
        return rule.validate(user.getAttributes());
    }
}
