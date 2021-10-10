package com.zpi.infrastructure.onlaunch

import com.zpi.domain.organization.Organization
import com.zpi.domain.organization.OrganizationRepository
import com.zpi.domain.organization.manager.Manager
import com.zpi.domain.organization.manager.ManagerRepository
import com.zpi.domain.organization.manager.Role
import spock.lang.Specification
import spock.lang.Subject;

class ConfigurationOnLunchUT extends Specification{

    def managerRepository = Mock(ManagerRepository)
    def organizationRepository = Mock(OrganizationRepository)

    @Subject
    ConfigurationOnLaunch onLaunch = new ConfigurationOnLaunch(managerRepository, organizationRepository)

    def 'should create super admin account and master organization on service start up'() {
        given:
            def superAdminName = 'admin'
            def masterOrganization = 'master'
            organizationRepository.findByName(masterOrganization) >> Optional.empty()
            managerRepository.findAllByRole(Role.SUPER_ADMIN) >> List.of()
        when:
            onLaunch.setDefaultConfiguration()
        then:
            1 * managerRepository.save({
                m -> {
                    (m as Manager).organizationName == masterOrganization
                    (m as Manager).username == superAdminName
                }
            })

            1 * organizationRepository.save({
                o -> {
                    (o as Organization).name == masterOrganization
                }
            })

    }
}

