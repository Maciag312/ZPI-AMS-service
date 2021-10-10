package com.zpi.api.organization.client;

import com.zpi.domain.organization.client.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    @NotNull
    @NotEmpty
    private String id;

    @NotNull
    private List<String> availableRedirectUri;

    public Client toDomain(String organizationName) {
        var client = new Client(id);
        client.getAvailableRedirectUri().addAll(availableRedirectUri);
        client.setOrganizationName(organizationName);
        return client;
    }
}
