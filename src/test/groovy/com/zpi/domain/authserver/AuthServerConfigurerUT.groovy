package com.zpi.domain.authserver

import com.zpi.domain.client.Client
import com.zpi.domain.client.ClientService
import com.zpi.infrastructure.persistance.authserver.InMemoryAuthServerConfigurationRepository
import com.zpi.infrastructure.persistance.client.InMemoryClientRepository
import spock.lang.Specification
import spock.lang.Subject

class AuthServerConfigurerUT extends Specification {

    def repo = new InMemoryAuthServerConfigurationRepository()

    @Subject
    def service = new AuthServerConfigurer(repo);

    def 'should create auth server configuration'() {
        given:
            def configuration = new AuthServerConfiguration(3123, 'secret')
        when:
            service.createOrUpdate(configuration)

        then:
             def config = service.get()
             config.tokenExpirationTime == 3123
             config.tokenSecretKey == 'secret'
    }

    def 'should update auth server configuration'() {
        given:
            def configuration = new AuthServerConfiguration(3123, 'secret')
            service.createOrUpdate(new AuthServerConfiguration(1, 'sec'))
        when:
            service.createOrUpdate(configuration)

        then:
            def config = service.get()
            config.tokenExpirationTime == 3123
            config.tokenSecretKey == 'secret'
    }
}
