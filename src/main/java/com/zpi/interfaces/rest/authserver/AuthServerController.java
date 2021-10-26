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
        if("1".equals(id)) {
            return Optional.of(new ClientDTO("1", Set.of("http://localhost:3000/")));
        }
        return clientService.getClient(id).map(ClientDTO::fromDomain);
    }
}
