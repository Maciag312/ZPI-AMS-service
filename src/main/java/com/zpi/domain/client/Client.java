package com.zpi.domain.client;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class Client {
    private final Set<String> availableRedirectUri = new HashSet<>();
    private final List<String> hardcodedDefaultScope = List.of("profile".split(" "));
    private final String id;

    @Setter
    private Set<String> availableGrantTypes = Set.of("authorization_code");
}
