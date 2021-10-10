package com.zpi.domain.organization;

import lombok.Getter;


@Getter
public class Organization {
    String name;

    public Organization(String name) {
        this.name = name;
    }
}
