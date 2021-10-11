package com.zpi.domain.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Manager {
    final String username;
    @Setter
    String password;
    final List<Role> roles;
}
