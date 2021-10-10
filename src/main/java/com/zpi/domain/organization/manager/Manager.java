package com.zpi.domain.organization.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.util.List;

@AllArgsConstructor
@Getter
public class Manager {
    final String username;
    @Setter
    String password;
    final String organizationName;
    final List<Role> roles;
}
