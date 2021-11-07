package com.zpi.domain.permission;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Permission {
    private final String permission;
    boolean isRemovable;
}
