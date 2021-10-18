package com.zpi.interfaces.rest.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RedirectURIsDTO {
    List<String> URIs;
}
