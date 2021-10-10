package com.zpi.domain.organization.manager

import com.zpi.infrastructure.security.jwt.JwtTokenProvider
import spock.lang.Specification
import spock.lang.Subject

class ManagerAccountManagerUT extends Specification {

    def repo = Mock(ManagerRepository)
    def tokenProvider = Mock(JwtTokenProvider)

    @Subject
    ManagerAccountManager accountManager = new ManagerAccountMangerImpl(repo, tokenProvider);

    def 'should get jwt token after manager successfully logs in'() {
        given:
            def organization = "pizza-house"
            def login = "manager"
            def password = "secret"
            def manager = new Manager(login, password, organization, List.of(Role.MANAGER))

            repo.findByUsername(login) >> Optional.of(manager)
            repo.findByOrganizationNameAndUsername(organization, login) >> Optional.of(manager)
            tokenProvider.createToken(login, List.of(Role.MANAGER)) >> "magic-token"
        when:
            def token = accountManager.signIn(organization, login, password)

        then:
            token == "magic-token"
    }
}
