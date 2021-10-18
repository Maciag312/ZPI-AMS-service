package com.zpi.interfaces.rest.authserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private String id;
    private List<String> availableRedirectUri;
}
