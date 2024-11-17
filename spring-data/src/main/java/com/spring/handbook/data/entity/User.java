package com.spring.handbook.data.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.spring.handbook.data.listener.UserListener;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Set;

@Entity
@DynamicUpdate
@DynamicInsert
@EntityListeners(UserListener.class)
@Table(name = "app_user")
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String firstName;

        private String lastName;

        @ManyToMany(cascade = CascadeType.ALL)
        @JoinTable(
                name = "app_user_role",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id")
        )
        @JsonManagedReference
        private Set<Role> roles;

        @OneToMany(mappedBy = "user")
        @JsonManagedReference
        private Set<Post> posts;

        @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
        @PrimaryKeyJoinColumn
        private Address address;

        // Default constructor
        public User() {
        }

        public User(Long id, String firstName, String lastName, Set<Role> roles, Set<Post> posts, Address address) {
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

        public Set<Role> getRoles() {
                return roles;
        }

        public void setRoles(Set<Role> roles) {
                this.roles = roles;
        }

        public Set<Post> getPosts() {
                return posts;
        }

        public void setPosts(Set<Post> posts) {
                this.posts = posts;
        }

        public Address getAddress() {
                return address;
        }

        public void setAddress(Address address) {
                address.setUser(this);
                this.address = address;
        }
}
