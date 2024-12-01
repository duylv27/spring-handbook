package com.spring.handbook.security.service.impl;

import com.spring.handbook.security.dto.AuthUserInfoDTO;
import com.spring.handbook.security.service.AuthenticationService;
import com.spring.handbook.security.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationServiceImpl(
            AuthenticationManager authenticationManager,
            JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public AuthUserInfoDTO login(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        final String token = jwtService.generateToken(username);
        final String refreshToken = jwtService.generateRefreshToken(username);
        return new AuthUserInfoDTO(username, token, refreshToken);
    }
}
