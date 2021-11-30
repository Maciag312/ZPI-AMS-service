package com.zpi.interfaces.rest.group;

import com.zpi.domain.group.rule.Rule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RuleDTO {
    String name;
    List<MatcherDTO> matchers;

    public static RuleDTO fromDomain(Rule rule) {
        return new RuleDTO(rule.getName(), rule.getMatchers().stream().map(MatcherDTO::fromDomain).collect(Collectors.toList()));
    }

    public Rule toDomain() {
        return new Rule(name, matchers.stream().map(MatcherDTO::toDomain).collect(Collectors.toList()));
    }
}
