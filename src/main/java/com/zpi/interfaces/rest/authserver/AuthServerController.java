package com.zpi.interfaces.rest.authserver;

import com.zpi.domain.client.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authserver")
public class AuthServerController {

    private final ClientService clientService;

    @GetMapping("/client/{id}/")
    public Optional<ClientDTO> clientDetails(@PathVariable String id) {
        return clientService.getClient(id).map(ClientDTO::fromDomain);
    }

    @GetMapping("/config")
    public AuthConfigurationDTO configuration() {
        return new AuthConfigurationDTO(new TokenConfigurationDTO(3600000, "DJASFDNUS812DAMNXMANSDHQHW83183JD18JJ1HFG8JXJ12JSH1XCHBUJ28X2JH12J182XJH1F3H1JS81G7RESHD13H71"));
    }
}
