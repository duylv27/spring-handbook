package com.spring.handbook.app.rest;

import com.spring.handbook.security.dto.AuthUserInfoDTO;
import com.spring.handbook.security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/public/login")
    public AuthUserInfoDTO authenticate(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        return authenticationService.login(username, password);
    }

}
