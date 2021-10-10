package com.zpi.infrastructure.organization.manager;

import com.zpi.domain.organization.manager.Manager;
import com.zpi.domain.organization.manager.Role;
import com.zpi.infrastructure.common.EntityTuple;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ManagerTuple implements EntityTuple<Manager> {

    private final String username;
    private final String password;
    private final List<Role> roles;

    private final String organizationName;


    @Override
    public Manager toDomain() {
        return null;
    }
}
