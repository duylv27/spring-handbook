package com.spring.handbook.data.controller;

import com.spring.handbook.data.dto.PostDTO;
import com.spring.handbook.data.service.PostService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public PostDTO create(@RequestParam String content, @RequestParam Long userId) {
        return postService.create(userId, content);
    }

}
