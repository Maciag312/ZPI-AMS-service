package com.zpi.domain.organization.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public boolean saveClient(Client client, String organizationName) {
        var foundClient = clientRepository.findByKey(client.getId());
        if (foundClient.isPresent())
            return false;
        client.setOrganizationName(organizationName);
        clientRepository.save(client.getId(), client);
        return true;
    }

    public Optional<Client> getClient(String id) {
        return clientRepository.findByKey(id);
    }

    public void addRedirectionURI(String client_name, String organization_name, String uri) {
        Optional<Client> client = clientRepository.findByNameAndOrganizationName(client_name, organization_name);
        if (client.isEmpty()) {
            throw new IllegalArgumentException("Client: " + client_name + " or organization: " + organization_name + "is not found.");
        }
        client.get().getAvailableRedirectUri().add(uri);
        clientRepository.save(client.get().getId(), client.get());
    }
}
