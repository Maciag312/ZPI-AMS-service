package com.zpi.api.organization.client;

import com.zpi.domain.organization.OrganizationService;
import com.zpi.domain.organization.client.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/organization/{organization_name}/client")
public class ClientController {
    private final ClientService service;
    private final OrganizationService organizationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody ClientDTO client, @PathVariable String organization_name) {
        throwWhenOrganizationIsNotFound(organization_name);
        var domain = client.toDomain(organization_name);
        if (service.saveClient(domain, organization_name)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PostMapping("/{client_name}/redirection_uris")
    public ResponseEntity<?> addRedirectionURI(@RequestBody RedirectUriDTO uri, @PathVariable String organization_name, @PathVariable String client_name) {
        try {
            service.addRedirectionURI(client_name, organization_name, uri.getRedirectURI());
        } catch (IllegalArgumentException notFoundOrganizationOrClient) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.created(
                createLocation(uri.getRedirectURI(), organization_name, client_name))
                .build();
    }

    private URI createLocation(String uri, String organization_name, String client_name) {
        return URI.create("/api/organization/"
                        + organization_name
                        + "/client/"
                        + client_name
                        + "/redirection_uris/"
                        + uri);
    }

    private void throwWhenOrganizationIsNotFound(String name) {
        if(!organizationService.exists(name)){
            throw new IllegalArgumentException("Organization with such name doesn't exists");
        }
    }
}
