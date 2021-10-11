package com.zpi.infrastructure.client;

import com.zpi.domain.client.Client;
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
    private final HashSet<String> availableRedirectUri;

    ClientTuple(Client client) {
        this.id = client.getId();
        this.availableRedirectUri = new HashSet<>(client.getAvailableRedirectUri());
    }

    @Override
    public Client toDomain() {
        var client = new Client(id);
        client.getAvailableRedirectUri().addAll(availableRedirectUri);
        return client;
    }
}
