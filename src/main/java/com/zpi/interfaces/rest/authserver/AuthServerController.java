package com.zpi.interfaces.rest.authserver;

import com.zpi.interfaces.rest.authserver.dto.ClientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authserver")
public class AuthServerController {

    @GetMapping("/client/{id}/")
    public Optional<ClientDTO> clientDetails(@PathVariable String id) {
         return Optional.ofNullable(clientDetailsMock(id));
    }

    private ClientDTO clientDetailsMock(String id) {
        if("1".equals(id)) {
            return new ClientDTO("1", List.of("http://localhost:3000/"));
        }
        return null;
    }
}
