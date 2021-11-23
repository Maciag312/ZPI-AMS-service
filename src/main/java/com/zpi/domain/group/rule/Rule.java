package com.zpi.domain.group.rule;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Value
public class Rule {
   List<List<Matcher>> orListsOfAndMatchers;

   void addAndMatcher(List<Matcher> matchersAndGroup) {
       orListsOfAndMatchers.add(matchersAndGroup);
   }

   boolean validate() {
       for (List<Matcher> andMatchers : orListsOfAndMatchers) {
           boolean andValid = true;
           for (Matcher matcher : andMatchers) {
                if(!matcher.validate()) {
                    andValid = false;
                }
           }
           if(andValid) {
               return true;
           }
       }
       return false;
   }
}
