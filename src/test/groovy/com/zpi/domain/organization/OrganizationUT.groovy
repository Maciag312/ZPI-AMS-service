package com.zpi.domain.organization

import com.zpi.domain.organization.manager.Manager
import com.zpi.domain.organization.manager.ManagerRepository
import spock.lang.Specification
import spock.lang.Subject

class OrganizationUT extends Specification {

    def organizationRepo = Mock(OrganizationRepository)
    def managerRepo = Mock(ManagerRepository)

    @Subject
    OrganizationService service = new OrganizationServiceImpl(organizationRepo, managerRepo);

    def 'when creating organization, default manager should be also created'() {
        given:
            def organizationName = 'pizza-house'
            organizationRepo.findByName(organizationName) >> Optional.empty()

        when:
            service.register(organizationName);

        then:
            1 * organizationRepo.save(o -> {
                (o as Organization).name == "pizza-house"
            })

            1 * managerRepo.save(m -> {
                (m as Manager).username == "admin"
                (m as Manager).password == "admin"
            })
    }
}
