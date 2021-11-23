package com.zpi.domain.permission;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class Permission {
    private final String permission;
    boolean isRemovable;
}
