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
        var op = matcher.getOperator().toLowerCase().replace("_", " ");
        return new MatcherDTO(matcher.getAttribute(), matcher.getExpected().toString(), matcher.getType().toString().toLowerCase(), op);
    }

    Matcher toDomain() {
        switch (type.toLowerCase()) {
            case "numeric" :
                var op = operator.toUpperCase().replace(" ", "_");
                return new NumericMatcher(attribute, Double.valueOf(expected), NumericMatcher.Operator.valueOf(op));
            case "string" :
                var strOp = operator.toUpperCase().replace(" ", "_");
                return new StringMatcher(attribute, expected, StringMatcher.Operator.valueOf(strOp));
            default:
                throw new IllegalArgumentException();
        }
    }
}
