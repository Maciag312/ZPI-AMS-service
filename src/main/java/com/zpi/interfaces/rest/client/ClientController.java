package com.zpi.interfaces.rest.client;

import com.zpi.domain.client.Client;
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
import java.util.Set;

import static java.util.stream.Collectors.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
@SecurityScheme(name = "client_auth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT", in = SecuritySchemeIn.HEADER)
public class ClientController {
    private final ClientService service;

    @Operation( security = {@SecurityRequirement(name = "client_auth") })
    @GetMapping
    public Set<ClientDTO> getAllClients() {
        return service.getAllClients().stream()
                .map(ClientDTO::fromDomain)
                .collect(toSet());
    }

    @Operation( security = {@SecurityRequirement(name = "client_auth") })
    @DeleteMapping("/{id}")
    public ResponseEntity removeURI(@PathVariable String id) {
        service.deleteClient(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation( security = {@SecurityRequirement(name = "client_auth") })
    @DeleteMapping("/{id}/redirection_uris")
    public ResponseEntity delete(@PathVariable String id, @RequestBody RedirectUriDTO uri) {
        service.removeURIfromClient(id, uri.getRedirectURI());
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

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
    @PostMapping("/{id}/redirection_uris")
    public ResponseEntity<?> addRedirectionURI(@RequestBody RedirectUriDTO uri, @PathVariable String id) {
        try {
            service.addRedirectionURI(id, uri.getRedirectURI());
        } catch (IllegalArgumentException notFoundOrganizationOrClient) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.created(
                createLocation(uri.getRedirectURI(), id))
                .build();
    }

    private URI createLocation(String uri, String client_name) {
        return URI.create("/api/client/"
                        + client_name
                        + "/redirection_uris/"
                        + uri);
    }

}
