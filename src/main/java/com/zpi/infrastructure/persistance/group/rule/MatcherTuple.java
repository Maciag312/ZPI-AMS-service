package com.zpi.infrastructure.persistance.group.rule;

import com.zpi.domain.group.rule.Matcher;
import com.zpi.domain.group.rule.NumericMatcher;
import com.zpi.domain.group.rule.StringMatcher;
import lombok.Value;
@Value
public class MatcherTuple {

    String type;
    String operator;
    String expected;
    String attribute;

    public static MatcherTuple fromDomain(Matcher matcher) {
        return new MatcherTuple(matcher.getType().toString(),
                                matcher.getOperator(),
                                matcher.getExpected().toString(),
                                matcher.getAttribute());
    }

    public static Matcher toDomain(MatcherTuple matcher) {
        if (matcher.type.equals("numeric")){
            return new NumericMatcher(matcher.attribute, Double.valueOf(matcher.expected), NumericMatcher.Operator.valueOf(matcher.operator));
        } else if (matcher.type.equals("string")) {
            return new StringMatcher(matcher.attribute, matcher.expected, StringMatcher.Operator.valueOf(matcher.operator));
        }
        return null;
    }
}
