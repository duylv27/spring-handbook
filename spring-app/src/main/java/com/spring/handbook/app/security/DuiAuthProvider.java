package com.spring.handbook.app.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class DuiAuthProvider implements AuthenticationProvider {

    private final Logger logger = LoggerFactory.getLogger(DuiAuthProvider.class);

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        logger.info("# Hit DuiAuthProvider");
        String username = auth.getName();
        String password = auth.getCredentials().toString();
        if ("externaluser".equals(username) && "pass".equals(password)) {
            return auth;
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
