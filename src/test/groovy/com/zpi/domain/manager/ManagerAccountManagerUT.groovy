package com.zpi.domain.manager

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
            def login = "manager"
            def password = "secret"
            def manager = new Manager(login, password , List.of(Role.MANAGER))

            repo.findByUsername(login) >> Optional.of(manager)
            tokenProvider.createToken(login, List.of(Role.MANAGER)) >> "magic-token"
        when:
            def token = accountManager.signIn( login, password)

        then:
            token == "magic-token"
    }
}
