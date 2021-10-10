package com.zpi.utils

import com.zpi.api.common.utils.HashGenerator
import spock.lang.Specification
import spock.lang.Subject

class HashGeneratorUT extends Specification {
    @Subject
    private HashGenerator hashGenerator = new HashGenerator()

    private String string1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
    private String string2 = "DifferentString"

    def "should return non empty for empty string"() {
        when:
            def hash = hashGenerator.generate("")

        then:
            hash.length() != 0
    }

    def "should return non empty hash for non empty string"() {
        when:
            def hash = hashGenerator.generate(string1)

        then:
            hash.length() != 0
    }

    def "should return hash not containing original string"() {
        when:
            def hash = hashGenerator.generate(string1)

        then:
            !hash.contains(string1)
    }

    def "should be the same for the same strings"() {
        when:
            HashGenerator hashGenerator1 = new HashGenerator()
            HashGenerator hashGenerator2 = new HashGenerator()
            def hash1 = hashGenerator1.generate(string1)
            def hash2 = hashGenerator2.generate(string1)

        then:
            hash1 == hash2
    }

    def "should be different for different strings"() {
        when:
            HashGenerator hashGenerator1 = new HashGenerator()
            HashGenerator hashGenerator2 = new HashGenerator()
            def hash1 = hashGenerator1.generate(string1)
            def hash2 = hashGenerator2.generate(string2)

        then:
            hash1 != hash2
    }
}
