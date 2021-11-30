package com.zpi.domain.group.rule;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static java.lang.String.format;

@Value
@Slf4j
public class NumericMatcher implements Matcher {
    String attribute;
    Double expected;
    Operator operator;

    @Override
    public boolean validate(Map<String, String> attributes) {
        if (!attributes.containsKey(attribute)) {
            return false;
        }
        var value = Double.valueOf(attributes.get(attribute));
        switch (operator) {
            case EQUAL:
                return value.equals(expected);
            case NOT_EQUAL:
                return !value.equals(expected);
            case GREATER:
                return value.compareTo(expected) > 0;
            case LESS:
                return value.compareTo(expected) < 0;
            case GREATER_OR_EQUAL:
                return value.compareTo(expected) >= 0;
            case LESS_OR_EQUAL:
                return value.compareTo(expected) <= 0;
            default:
                log.warn(format("Validation has unhandled operator=[%s]", operator));
                return false;
        }
    }

    @Override
    public Object getType() {
        return "numeric";
    }

    @Override
    public String getOperator() {
        return operator.name();
    }

    public enum Operator {
        EQUAL, NOT_EQUAL, GREATER, LESS, GREATER_OR_EQUAL, LESS_OR_EQUAL
    }
}
