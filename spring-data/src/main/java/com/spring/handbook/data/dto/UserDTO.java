package com.spring.handbook.data.dto;

import java.util.Set;

public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private Set<RoleDTO> roles;
    private Set<PostDTO> posts;
    private AddressDTO address;

    // Default constructor
    public UserDTO() {
    }

    public UserDTO(Long id, String firstName, String lastName, Set<RoleDTO> roles, Set<PostDTO> posts, AddressDTO address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
        this.posts = posts;
        this.address = address;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    public Set<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(Set<PostDTO> posts) {
        this.posts = posts;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}