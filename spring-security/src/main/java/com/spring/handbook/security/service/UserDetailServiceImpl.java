package com.spring.handbook.security.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(
                username,
                "$2a$12$P2uuCduZvzq9YfImZMP6furcJusZE80sU61tHCAJSJ6SgombIwiWi", // bcrypt encoder for 'password'
                Set.of(new SimpleGrantedAuthority("ADMIN"))
        );
    }
}
