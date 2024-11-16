package com.spring.handbook.data.controller;

import com.spring.handbook.data.dto.UserDTO;
import com.spring.handbook.data.entity.User;
import com.spring.handbook.data.repository.UserRepository;
import com.spring.handbook.data.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("save-then-get")
    public User getUser() {
        return userService.saveThenGet();
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @PutMapping("{id}")
    public UserDTO update(@PathVariable Long id) {
        return userService.update(id);
    }

}
