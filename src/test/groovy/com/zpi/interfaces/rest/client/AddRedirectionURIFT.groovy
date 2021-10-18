package com.zpi.interfaces.rest.client

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
class AddRedirectionURIFT extends Specification {
    @Autowired
    private MockMvc mockMvc

    @Autowired
    private ClientRepository repository


    @Autowired
    private CommonHelpers commonHelpers

    def setup() {
        repository.clear()
    }

    private static final String registerClientUrl = '/api/client'
    private static final String addRedirectionURIUrl = '/api/client/' +  CommonFixtures.clientDTO().id + "/redirection_uris"


    def "should register new client and add redirection URI"() {
        given:
            def client = CommonFixtures.clientDTO()
            def redirectionURI = "/some/redirection/uri"

        when:
            def requestRegisterClient= commonHelpers.postRequest(client, registerClientUrl)
            def requestAddUri = commonHelpers.postRequest(redirectionURI, addRedirectionURIUrl)


        then:
            requestRegisterClient.andExpect(status().isCreated())
            requestAddUri.andExpect(status().isCreated())

        and:
            def domainClient = client.toDomain()
            def clientResult = repository.findByKey(domainClient.getId()).get()
            def redirectionResult = clientResult.availableRedirectUri
            redirectionResult.contains(redirectionURI)
    }
}
