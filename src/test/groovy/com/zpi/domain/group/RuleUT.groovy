package com.zpi.domain.group

import com.zpi.domain.group.rule.NumericMatcher
import com.zpi.domain.group.rule.Rule
import com.zpi.domain.group.rule.StringMatcher
import spock.lang.Specification

class RuleUT extends Specification {

    def 'the given attributes should satisfy defined policy rule'() {

    }

    def 'the given attributes should not satisfy defined policy rule'() {

    }

    def 'given numeric attribute should satisfy expected value defined in policy #attributeValue #operator #attributeExpected'() {
        expect:
            def numericMatcher = new NumericMatcher("value", attributeExpected, operator)
            def rule = new Rule("rule", [numericMatcher])
            rule.validate(["value": attributeValue] as Map)
        where:
            attributeValue | attributeExpected | operator
            "144"          | 143d              | NumericMatcher.Operator.GREATER
            "120"          | 120d              | NumericMatcher.Operator.GREATER_OR_EQUAL
            "144"          | 145               | NumericMatcher.Operator.LESS
            "144"          | 144d              | NumericMatcher.Operator.LESS_OR_EQUAL
            "10"           | 10d               | NumericMatcher.Operator.EQUAL
            "11111"        | 312d              | NumericMatcher.Operator.NOT_EQUAL
    }

    def 'given numeric attribute should not satisfy expected value defined in policy #attributeValue #operator #attributeExpected'() {
        expect:
            def numericMatcher = new NumericMatcher("value", attributeExpected, operator)
            def rule = new Rule("rule", [numericMatcher])
            !rule.validate(["value": attributeValue] as Map)
        where:
            attributeValue | attributeExpected | operator
            "143"          | 150              | NumericMatcher.Operator.GREATER
            "120"          | 123              | NumericMatcher.Operator.GREATER_OR_EQUAL
            "150"          | 145              | NumericMatcher.Operator.LESS
            "145"          | 99               | NumericMatcher.Operator.LESS_OR_EQUAL
            "11"           | 10               | NumericMatcher.Operator.EQUAL
            "312"          | 312              | NumericMatcher.Operator.NOT_EQUAL
    }

    def 'given string attribute should satisfy expected value defined in policy #attributeValue #operator #attributeExpected'() {
        expect:
        def numericMatcher = new StringMatcher("value", attributeExpected, operator)
        def rule = new Rule("rule", [numericMatcher])
        rule.validate(["value": attributeValue] as Map)
        where:
        attributeValue | attributeExpected | operator

        "Lorem Ipsum"          | "Lorem Ips"               | StringMatcher.Operator.STARTS_WITH
        "Lorem Ipsum"          | "Lorem Ipsum"             | StringMatcher.Operator.EQUAL
        "Lorem Ipsum"          | "Ipsum"                   | StringMatcher.Operator.CONTAINS
        "Lorem Ipsum"          | "Lorem Isum"              | StringMatcher.Operator.NOT_EQUAL
    }

    def 'given string attribute should not satisfy expected value defined in policy #attributeValue #operator #attributeExpected'() {
        expect:
            def numericMatcher = new StringMatcher("value", attributeExpected, operator)
            def rule = new Rule("rule", [numericMatcher])
            !rule.validate(["value": attributeValue] as Map)
        where:
        attributeValue | attributeExpected | operator

        "Lorem      "          | "Lorem Ipsum "             | StringMatcher.Operator.STARTS_WITH
        "Lorem Ipsum"          | "Lorem Ip sum"             | StringMatcher.Operator.EQUAL
        "Lorem Ipsum"          | "Ipsm"                     | StringMatcher.Operator.CONTAINS
        "Lorem Ipsum"          | "Lorem Ipsum"              | StringMatcher.Operator.NOT_EQUAL
    }

}
