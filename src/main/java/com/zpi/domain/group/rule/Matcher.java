package com.zpi.domain.group.rule;

import java.util.Map;

public interface Matcher {
    boolean validate(Map<String, String> attributes);
    Object getType();
    String getOperator();
    Object getExpected();
    String getAttribute();
}
