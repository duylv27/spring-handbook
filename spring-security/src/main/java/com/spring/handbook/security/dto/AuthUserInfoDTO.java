package com.spring.handbook.security.dto;

public record AuthUserInfoDTO(
        String username,
        String token,
        String refreshToken
) {}
