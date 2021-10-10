package com.zpi.api.organization.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ManagerDTO {
    private String username;
    private String password;
    private String role;
}
