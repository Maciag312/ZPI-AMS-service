package com.zpi.api.client


import com.zpi.CommonFixtures
import com.zpi.CommonHelpers
import com.zpi.domain.client.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class ClientRegistrationFT extends Specification {
    @Autowired
    private MockMvc mockMvc

    @Autowired
    private ClientRepository repository


    @Autowired
    private CommonHelpers commonHelpers

    def setup() {
        repository.clear()
    }

    private static final String url = '/api/client'

    def "should register new client"() {
        given:
            def client = CommonFixtures.clientDTO()

        when:
            def request = commonHelpers.postRequest(client, url)

        then:
            request.andExpect(status().isCreated())

        and:
            def domainClient = client.toDomain()
            def result = repository.findByKey(domainClient.getId()).get()

            result == domainClient
    }

    def "should return conflict on clientId crash"() {
        given:
            def clientA = CommonFixtures.clientDTO()
            def clientB = CommonFixtures.clientDTO()

        when:
            commonHelpers.postRequest(clientA, url)
            def request = commonHelpers.postRequest(clientB, url)

        then:
            request.andExpect(status().isConflict())

        and:
            def domainClientA = clientA.toDomain()
            def result = repository.findByKey(domainClientA.getId()).get()

            result == domainClientA
    }

    def "should return bad request on null client"() {
        given:
            def client = null

        when:
            def request = commonHelpers.postRequest(client, url)

        then:
            request.andExpect(status().isBadRequest())
    }

    def "should return bad request on malformed client"() {
        given:
            def client = ClientDTO.builder().id("").build()

        when:
            def request = commonHelpers.postRequest(client, url)

        then:
            request.andExpect(status().isBadRequest())
    }
}
