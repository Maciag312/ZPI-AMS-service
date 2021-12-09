package com.zpi.domain.group.rule;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static java.lang.String.format;

@Slf4j
@Value
public class  StringMatcher implements Matcher{
    String attribute;
    String expected;
    Operator operator;

    @Override
    public boolean validate(Map<String, String> attributes) {
        if(!attributes.containsKey(attribute)) {
            return false;
        }
        var value = attributes.get(attribute);
        switch (operator) {
            case EQUAL:
                return value.equals(expected);
            case NOT_EQUAL:
                return !value.equals(expected);
            case CONTAINS:
                return value.contains(expected);
            case STARTS_WITH:
                return value.startsWith(expected);
            default:
                log.warn(format("Validation has unhandled operator=[%s]", operator));
                return false;
        }
    }

    @Override
    public Object getType() {
        return "string";
    }

    @Override
    public String getOperator() {
        return operator.name();
    }

    public enum Operator {
        EQUAL, NOT_EQUAL, CONTAINS, STARTS_WITH
    }
}
