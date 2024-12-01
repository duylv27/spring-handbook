package com.spring.handbook.app.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/private")
public class PrivateController {

    @GetMapping
    public Map<String, String> get() {
        return Map.of("message", "Hi, there");
    }

}
