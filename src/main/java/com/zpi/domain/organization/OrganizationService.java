package com.zpi.domain.organization;

import org.hibernate.NonUniqueObjectException;

public interface OrganizationService {
    void register(String name) throws NonUniqueObjectException;
    boolean exists(String name);
}
