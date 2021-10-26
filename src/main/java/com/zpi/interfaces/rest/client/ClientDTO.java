package com.zpi.interfaces.rest.client;

import com.zpi.domain.client.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    @NotNull
    @NotEmpty
    private String id;

    @NotNull
    private Set<String> availableRedirectUris;

    public Client toDomain() {
        var client = new Client(id);
        client.getAvailableRedirectUri().addAll(availableRedirectUris);
        return client;
    }
    public static ClientDTO fromDomain(Client client) {
        return new ClientDTO(client.getId(), client.getAvailableRedirectUri());
    }
}
