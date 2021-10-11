package com.zpi.domain.manager;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    SUPER_ADMIN,
    CLIENT,
    MANAGER;
    public String getAuthority() {
        return name();
    }
}