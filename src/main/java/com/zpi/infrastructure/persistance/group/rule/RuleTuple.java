package com.zpi.infrastructure.persistance.group.rule;

import com.zpi.domain.group.rule.Rule;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class RuleTuple {
    String name;
    List<MatcherTuple> matchers;

    public static RuleTuple fromDomain(Rule rule) {
        return new RuleTuple(rule.getName(), rule.getMatchers().stream()
                                                        .map(MatcherTuple::fromDomain)
                                                        .collect(Collectors.toList()));
    }

    public static Rule toDomain(RuleTuple rule) {
        return new Rule(rule.name, rule.matchers.stream()
                                        .map(MatcherTuple::toDomain)
                                        .collect(Collectors.toList()));
    }
}
