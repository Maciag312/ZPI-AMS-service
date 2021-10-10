package com.zpi.domain.common;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class RequestError<ErrorType> {
    private final ErrorType error;
    private final String errorDescription;
    private final String state;
}
