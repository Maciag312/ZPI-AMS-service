package com.zpi.interfaces.rest.authserver;

import com.zpi.domain.client.ClientService;
import com.zpi.interfaces.rest.authserver.dto.ClientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authserver")
public class AuthServerController {

    private final ClientService clientService;

    @GetMapping("/client/{id}/")
    public Optional<ClientDTO> clientDetails(@PathVariable String id) {
        return clientService.getClient(id).map(ClientDTO::fromDomain);
    }
}
