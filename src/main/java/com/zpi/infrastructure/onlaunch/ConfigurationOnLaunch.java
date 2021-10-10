package com.zpi.infrastructure.onlaunch;

import com.zpi.domain.organization.Organization;
import com.zpi.domain.organization.OrganizationRepository;
import com.zpi.domain.organization.manager.Manager;
import com.zpi.domain.organization.manager.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.zpi.domain.organization.manager.Role.*;

@Component
@RequiredArgsConstructor
public class ConfigurationOnLaunch {

    private final ManagerRepository managerRepository;
    private final OrganizationRepository organizationRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void setDefaultConfiguration() {
        var masterOrganization = "master";
        if (organizationRepository.findByName(masterOrganization).isEmpty()) {
            organizationRepository.save(new Organization(masterOrganization));
        }
        if  (managerRepository.findAllByRole(SUPER_ADMIN).isEmpty()) {
            var adminName = "admin";
            var adminPassword = "admin";
            var superAdmin = new Manager(adminName, adminPassword, masterOrganization, List.of(SUPER_ADMIN));
            managerRepository.save(superAdmin);
        }
    }
}
