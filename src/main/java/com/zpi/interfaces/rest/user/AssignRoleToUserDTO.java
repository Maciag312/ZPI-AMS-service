package com.zpi.interfaces.rest.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AssignRoleToUserDTO {
    String email;
    String role;
}
