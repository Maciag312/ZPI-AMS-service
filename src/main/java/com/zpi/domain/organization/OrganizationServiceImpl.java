package com.zpi.domain.organization;

import com.zpi.domain.organization.manager.Manager;
import com.zpi.domain.organization.manager.ManagerRepository;
import com.zpi.domain.organization.manager.Role;
import lombok.RequiredArgsConstructor;
import org.hibernate.NonUniqueObjectException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final ManagerRepository managerRepository;

    @Override
    public void register(String name) throws NonUniqueObjectException {
        if (organizationRepository.findByName(name).isPresent()) {
           throw new NonUniqueObjectException("Organization with such name already exists", -1, name);
        }
        Manager manager = new Manager("admin", "admin", name, List.of(Role.MANAGER));
        var organization = new Organization(name);
        managerRepository.save(manager);
        organizationRepository.save(organization);
    }

    @Override
    public boolean exists(String name) {
        return organizationRepository.findByName(name).isPresent();
    }
}
