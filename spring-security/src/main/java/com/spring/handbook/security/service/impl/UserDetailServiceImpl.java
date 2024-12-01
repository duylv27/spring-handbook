package com.spring.handbook.security.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Value("${application.security.default.password}")
    private String bcryptPassword;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("# Hit UserDetailServiceImpl");
        return new User(
                username,
                bcryptPassword,
                Set.of(new SimpleGrantedAuthority("ADMIN"))
        );
    }
}
