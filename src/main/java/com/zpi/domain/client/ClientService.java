package com.zpi.domain.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public boolean saveClient(Client client) {
        var foundClient = clientRepository.findByKey(client.getId());
        if (foundClient.isPresent())
            return false;
        clientRepository.save(client.getId(), client);
        return true;
    }

    public Optional<Client> getClient(String id) {
        return clientRepository.findByKey(id);
    }

    public void addRedirectionURI(String client_name, String uri) {
        Optional<Client> client = clientRepository.findByName(client_name);
        if (client.isEmpty()) {
            throw new IllegalArgumentException("Client: " + client_name +  "is not found.");
        }
        client.get().getAvailableRedirectUri().add(uri);
        clientRepository.save(client.get().getId(), client.get());
    }
}
