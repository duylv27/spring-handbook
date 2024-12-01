package com.spring.handbook.security.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String generateToken(String userName);
    String generateRefreshToken(String userName);
    String extractUsernameFromToken(String token);
    boolean isTokenValid(String token, UserDetails userDetails);

}
