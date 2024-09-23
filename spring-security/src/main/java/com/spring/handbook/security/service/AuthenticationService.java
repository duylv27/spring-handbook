package com.spring.handbook.security.service;

import com.spring.handbook.security.dto.AuthUserInfoDTO;

public interface AuthenticationService {

    AuthUserInfoDTO login(String username, String password);

}
