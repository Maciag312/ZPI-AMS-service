package com.zpi

import com.zpi.api.organization.client.ClientDTO
import com.zpi.api.common.dto.UserDTO
import com.zpi.domain.organization.client.Client

class CommonFixtures {
    public static final String clientId = "id"
    public static final String redirectUri = "uri"
    public static final String login = "Login"
    public static final String password = "Password"

    static Client client() {
        def client = new Client(clientId)
        client.getAvailableRedirectUri().add(redirectUri)

        return client
    }

    static ClientDTO clientDTO() {
        return ClientDTO.builder()
                .id(clientId)
                .availableRedirectUri(List.of(redirectUri))
                .build()
    }

    static UserDTO userDTO() {
        return new UserDTO(login, password);
    }

}
