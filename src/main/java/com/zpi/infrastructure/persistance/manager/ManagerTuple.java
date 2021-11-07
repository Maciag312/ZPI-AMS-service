package com.zpi.infrastructure.persistance.manager;

import com.zpi.domain.manager.Manager;
import com.zpi.domain.manager.Role;
import com.zpi.infrastructure.common.EntityTuple;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ManagerTuple implements EntityTuple<Manager> {

    private final String username;
    private final String password;
    private final List<Role> roles;

    @Override
    public Manager toDomain() {
        return null;
    }
}
