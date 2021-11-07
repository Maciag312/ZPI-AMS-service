package com.zpi.domain.user;

import com.zpi.domain.permission.Permission;
import com.zpi.domain.role.Role;
import lombok.*;

import java.util.Set;

@Getter
@Builder
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
public class UserInfo {
    String email;
    Set<Permission> permissions;
    Set<Role> roles;
}
