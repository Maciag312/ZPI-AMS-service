package com.zpi.interfaces.rest.authserver;

import com.zpi.domain.client.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private String id;
    private Set<String> availableRedirectUri;

    public static ClientDTO fromDomain(Client client) {
        return new ClientDTO(client.getId(), client.getAvailableRedirectUri());
    }
}
