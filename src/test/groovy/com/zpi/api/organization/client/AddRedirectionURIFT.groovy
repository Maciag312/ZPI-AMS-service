package com.zpi.api.organization.client

import com.zpi.CommonFixtures
import com.zpi.CommonHelpers
import com.zpi.domain.organization.OrganizationRepository
import com.zpi.domain.organization.client.ClientRepository
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
    private OrganizationRepository organizationRepository;

    @Autowired
    private CommonHelpers commonHelpers

    private static final String organizationName = "pizzaHouse"
    private static final String registerClientUrl = '/api/organization/' + organizationName + '/client/register'
    private static final String addRedirectionURIUrl = '/api/organization/' + organizationName + '/client/' +  CommonFixtures.clientDTO().id + "/redirection_uris"

    def setup() {
        repository.clear()
        organizationRepository.clear();
    }

    def "should register new client and add redirection URI"() {
        given:
            def client = CommonFixtures.clientDTO()
            def redirectionURI = "/some/redirection/uri"

        when:
            commonHelpers.postRequest( '/api/organization/register/' + organizationName)
            def requestRegisterClient= commonHelpers.postRequest(client, registerClientUrl)
            def requestAddUri = commonHelpers.postRequest(redirectionURI, addRedirectionURIUrl)


        then:
            requestRegisterClient.andExpect(status().isCreated())
            requestAddUri.andExpect(status().isCreated())

        and:
            def domainClient = client.toDomain(organizationName)
            def clientResult = repository.findByKey(domainClient.getId()).get()
            def redirectionResult = clientResult.availableRedirectUri
            redirectionResult.contains(redirectionURI)
    }
}
