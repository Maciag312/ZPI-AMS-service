package com.zpi.infrastructure.organization;

import com.zpi.domain.organization.Organization;
import com.zpi.infrastructure.common.EntityTuple;
import lombok.Value;

@Value
public class OrganizationTuple implements EntityTuple<Organization> {

    String name;

    @Override
    public Organization toDomain() {
        return new Organization(name);
    }
}
