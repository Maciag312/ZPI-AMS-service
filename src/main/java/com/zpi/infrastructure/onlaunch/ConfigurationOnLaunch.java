package com.zpi.infrastructure.onlaunch;

import com.zpi.domain.client.Client;
import com.zpi.domain.client.ClientRepository;
import com.zpi.domain.manager.Manager;
import com.zpi.domain.manager.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.zpi.domain.manager.Role.*;

@Component
@RequiredArgsConstructor
public class ConfigurationOnLaunch {

    private final ManagerRepository managerRepository;
    private final ClientRepository clientRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void setDefaultConfiguration() {
        if  (managerRepository.findAllByRole(SUPER_ADMIN).isEmpty()) {
            var adminName = "admin";
            var adminPassword = "admin";
            var superAdmin = new Manager(adminName, adminPassword, List.of(SUPER_ADMIN));
            managerRepository.save(superAdmin);
            seedDatabaseWithSamples();
        }
    }

    private void seedDatabaseWithSamples() {
        seedDatabaseWithClients();
    }
    private void seedDatabaseWithClients(){
        Client client = new Client("1");
        if(clientRepository.findByName(client.getId()).isEmpty()) {
            client.getAvailableRedirectUri().add("http://localhost:3000/");
            clientRepository.save(client.getId(), client);
        }
    }
}
