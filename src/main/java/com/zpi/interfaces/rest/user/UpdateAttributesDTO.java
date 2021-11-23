package com.zpi.interfaces.rest.user;

import lombok.Value;

import java.util.HashMap;

@Value
public class UpdateAttributesDTO {
    String userEmail;
    HashMap<String, String> attributes;
}
