package com.zpi.interfaces.rest.token;

import com.zpi.infrastructure.security.jwt.JwtTokenProvider;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/token")
public class TokenController {

    private final JwtTokenProvider tokenProvider;

    @GetMapping("/{jwtToken}/isvalid")
    public boolean isValid(@PathVariable String jwtToken) {
        try {
            return tokenProvider.validateToken(jwtToken);
        } catch (JwtException ex) {
            return false;
        }
    }

}
