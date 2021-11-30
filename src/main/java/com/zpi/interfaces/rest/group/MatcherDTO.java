package com.zpi.interfaces.rest.group;

import com.zpi.domain.group.rule.Matcher;
import com.zpi.domain.group.rule.NumericMatcher;
import com.zpi.domain.group.rule.StringMatcher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MatcherDTO {
    String attribute;
    String expected;
    String type;
    String operator;

    static MatcherDTO fromDomain(Matcher matcher) {
        return new MatcherDTO(matcher.getAttribute(), matcher.getExpected().toString(), matcher.getType().toString(), matcher.getOperator());
    }

    Matcher toDomain() {
        switch (type) {
            case "numeric" :
                return new NumericMatcher(attribute, Double.valueOf(expected), NumericMatcher.Operator.valueOf(operator));
            case "string" :
                 return new StringMatcher(attribute, expected, StringMatcher.Operator.valueOf(operator));
            default:
                throw new IllegalArgumentException();
        }
    }
}
