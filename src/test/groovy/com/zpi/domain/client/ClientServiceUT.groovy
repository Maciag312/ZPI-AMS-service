package com.zpi.domain.client;


import com.zpi.infrastructure.persistance.client.InMemoryClientRepository;
import spock.lang.Specification
import spock.lang.Subject;

class ClientServiceUT extends Specification {

    def clientRepo = new InMemoryClientRepository()

    @Subject
    def service = new ClientService(clientRepo);

    def 'should add redirection uris to the client'() {
        given:
            def uri1 = "https://localhost:3000"
            def uri2 = "https://localhost:3002"
            def client = new Client('1')
            clientRepo.save('1', client);
        when:
            service.addRedirectionURI('1', uri1)
            service.addRedirectionURI('1', uri2)

        then:
            service.getClient('1').get().availableRedirectUri ==
                    ["https://localhost:3000", "https://localhost:3002"] as Set
    }

    def 'should remove redirection uris from the client'() {
        given:
            def uri1 = "https://localhost:3000"
            def uri2 = "https://localhost:3002"
            def client = new Client('1')
            client.getAvailableRedirectUri().addAll([uri1, uri2] as Set)
            clientRepo.save('1', client);
        when:
            service.removeURIfromClient('1', uri2)

        then:
            service.getClient('1').get().availableRedirectUri ==
                ["https://localhost:3000"] as Set
    }
}
