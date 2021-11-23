package com.zpi.domain.user;

import com.zpi.domain.group.Group;
import com.zpi.domain.role.Role;
import com.zpi.infrastructure.persistance.group.InMemoryGroupRepository;
import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Builder
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
public class User {
    private final String username;
    private final String email;
    public Set<Group> groups;
    private boolean isActive;
    final Set<Role> roles = new HashSet<>();
    private String password;
    private Map<String, String> attributes = new HashMap<>();

    public String renewPassword(int length) {
        password = RandomStringUtils.randomAlphanumeric(length);
        return password;
    }

    public void activate() {
        isActive = true;
    }

    public void deactivate() {
        isActive = false;
    }

    public void updateAttributes(HashMap<String, String> attributes) {
        this.attributes = attributes;
    }
}
