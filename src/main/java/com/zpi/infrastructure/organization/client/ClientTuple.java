package com.zpi.infrastructure.organization.client;

import com.zpi.domain.organization.client.Client;
import com.zpi.infrastructure.common.EntityTuple;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Id;
import java.util.HashSet;

@Getter
@Data
class ClientTuple implements EntityTuple<Client> {
    @Id
    private final String id;

    private final String organizationName;
    private final HashSet<String> availableRedirectUri;

    ClientTuple(Client client) {
        this.id = client.getId();
        this.availableRedirectUri = new HashSet<>(client.getAvailableRedirectUri());
        this.organizationName = client.getOrganizationName();
    }

    @Override
    public Client toDomain() {
        var client = new Client(id);
        client.getAvailableRedirectUri().addAll(availableRedirectUri);
        client.setOrganizationName(organizationName);
        return client;
    }
}
