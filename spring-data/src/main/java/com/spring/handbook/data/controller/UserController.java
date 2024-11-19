package com.spring.handbook.data.controller;

import com.spring.handbook.data.dto.BaseUserDTO;
import com.spring.handbook.data.dto.UserDTO;
import com.spring.handbook.data.entity.User;
import com.spring.handbook.data.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public BaseUserDTO getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("save-then-get")
    public User saveAndGet() {
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

    @PutMapping("{id}/non-txn")
    public UserDTO updateInNonTransactionContext(@PathVariable Long id) {
        return userService.updateInNonTransactionContext(id);
    }

}
