package com.zpi.user

import com.zpi.CommonFixtures
import com.zpi.api.common.dto.UserDTO
import com.zpi.domain.user.UserAuthenticator
import com.zpi.domain.user.UserRepository
import spock.lang.Specification
import spock.lang.Subject

class UserAuthenticationUT extends Specification {
    def userRepository = Mock(UserRepository)

    @Subject
    private UserAuthenticator authenticator = new UserAuthenticator(userRepository)

    def "should return true when credentials match"() {
        given:
            def user = CommonFixtures.userDTO().toHashedDomain()
            userRepository.findByKey(user.getLogin()) >> Optional.of(user)

        when:
            def isAuthenticated = authenticator.isAuthenticated(user)

        then:
            isAuthenticated
    }

    def "should return false when user does not exist"() {
        given:
            def user = CommonFixtures.userDTO().toHashedDomain()
            userRepository.findByKey(user.getLogin()) >> Optional.empty()

        when:
            def isAuthenticated = authenticator.isAuthenticated(user)

        then:
            !isAuthenticated
    }

    def "should return false when credentials does not match"() {
        given:
            def user = CommonFixtures.userDTO().toHashedDomain()
            def savedUser = UserDTO.builder()
                    .login(user.getLogin())
                    .password(user.getPassword() + "asdf")
                    .build().toDomain()

            userRepository.findByKey(user.getLogin()) >> Optional.of(savedUser)

        when:
            def isAuthenticated = authenticator.isAuthenticated(user)

        then:
            !isAuthenticated
    }
}
