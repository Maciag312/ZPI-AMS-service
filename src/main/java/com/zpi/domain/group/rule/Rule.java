package com.zpi.domain.group.rule;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
@Value
public class Rule {
    String name;
    List<Matcher> matchers;

   public boolean validate(Map<String, String> attributes) {
       for (Matcher matcher : matchers) {
           if (!matcher.validate(attributes)) {
               return false;
           }
       }
       return true;
   }
}
