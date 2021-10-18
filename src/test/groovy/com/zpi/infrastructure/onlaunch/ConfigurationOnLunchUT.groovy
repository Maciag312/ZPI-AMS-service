package com.zpi.infrastructure.onlaunch

import com.zpi.domain.manager.Manager
import com.zpi.domain.manager.ManagerRepository
import com.zpi.domain.manager.Role
import spock.lang.Specification
import spock.lang.Subject;

class ConfigurationOnLunchUT extends Specification{

    def managerRepository = Mock(ManagerRepository)

    @Subject
    ConfigurationOnLaunch onLaunch = new ConfigurationOnLaunch(managerRepository)

    def 'should create super admin account and master organization on service start up'() {
        given:
            def superAdminName = 'admin'
            managerRepository.findAllByRole(Role.SUPER_ADMIN) >> List.of()
        when:
            onLaunch.setDefaultConfiguration()
        then:
            1 * managerRepository.save({
                m -> {
                    (m as Manager).username == superAdminName
                }
            })

    }
}

