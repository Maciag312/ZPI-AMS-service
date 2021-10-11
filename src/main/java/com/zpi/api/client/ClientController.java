package com.zpi.api.client;

import com.zpi.domain.client.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class ClientController {
    private final ClientService service;

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody ClientDTO client) {
        var domain = client.toDomain();
        if (service.saveClient(domain)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

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
