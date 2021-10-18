package com.zpi.api.client;

import com.zpi.domain.client.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
@SecurityScheme(name = "client_auth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT", in = SecuritySchemeIn.HEADER)
public class ClientController {
    private final ClientService service;

    @Operation( security = {@SecurityRequirement(name = "client_auth") })
    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody ClientDTO client) {
        var domain = client.toDomain();
        if (service.saveClient(domain)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @Operation( security = {@SecurityRequirement(name = "client_auth") })
    @PostMapping("/{client_name}/redirection_uris")
    public ResponseEntity<?> addRedirectionURI(@RequestBody RedirectUriDTO uri, @PathVariable String client_name) {
        try {
            service.addRedirectionURI(client_name, uri.getRedirectURI());
        } catch (IllegalArgumentException notFoundOrganizationOrClient) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.created(
                createLocation(uri.getRedirectURI(), client_name))
                .build();
    }

    private URI createLocation(String uri, String client_name) {
        return URI.create("/api/client/"
                        + client_name
                        + "/redirection_uris/"
                        + uri);
    }

}
