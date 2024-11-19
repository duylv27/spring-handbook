package com.spring.handbook.data.dto;

public class PostDTO {

    private Long id;

    private String content;

    private Long userId;

    public PostDTO(Long id, String content, Long userId) {
        this.id = id;
        this.content = content;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
