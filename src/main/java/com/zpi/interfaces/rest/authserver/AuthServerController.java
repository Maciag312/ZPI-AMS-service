package com.zpi.interfaces.rest.authserver;

import com.zpi.domain.authserver.AuthServerConfigurer;
import com.zpi.domain.client.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authserver")
public class AuthServerController {

    private final ClientService clientService;
    private final AuthServerConfigurer authServerConfigurer;

    @GetMapping("/client/{id}/")
    public Optional<ClientDTO> clientDetails(@PathVariable String id) {
        return clientService.getClient(id).map(ClientDTO::fromDomain);
    }

    @GetMapping("/config")
    public AuthConfigurationDTO configuration() {
        return AuthConfigurationDTO.fromDomain(authServerConfigurer.get());
    }

    @PostMapping("/config")
    public void setConfiguration(@RequestBody AuthConfigurationDTO configuration) {
        authServerConfigurer.createOrUpdate(configuration.toDomain());
    }
}
