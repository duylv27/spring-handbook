package com.spring.handbook.data.service;

import com.spring.handbook.data.dto.PostDTO;
import com.spring.handbook.data.entity.Post;
import com.spring.handbook.data.repository.PostRepository;
import com.spring.handbook.data.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public PostService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public PostDTO create(Long userId, String content) {
        var user = userRepository.getReferenceById(userId); // #1
        var post = new Post();
        post.setContent(content);
        post.setUser(user);

        // If user does not exist, database will throw an exception
        postRepository.save(post);

        return new PostDTO(post.getId(), post.getContent(), user.getId());
    }

}
