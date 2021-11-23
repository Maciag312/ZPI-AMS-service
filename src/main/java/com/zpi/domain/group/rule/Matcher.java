package com.zpi.domain.group.rule;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import static java.lang.String.format;

@Slf4j
@Value
public class Matcher {
    String attribute;
    String expected;
    Type type;

    boolean validate() {
        switch (type) {
            case EQUAL:
                return attribute.equals(expected);
            case NOT_EQUAL:
                return !attribute.equals(expected);
            default:
                log.warn(format("Validation has unhandled type=[%s]", type));
                return false;
        }
    }

    public enum Type {
        EQUAL, NOT_EQUAL
    }
}
