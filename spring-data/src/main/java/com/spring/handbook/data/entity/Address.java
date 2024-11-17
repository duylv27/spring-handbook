package com.spring.handbook.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_user_address")
public class Address {

        @Id
        @Column(name = "user_id")
        private Long id;

        private String value;

        @OneToOne(fetch = FetchType.LAZY)
        @MapsId
        @JoinColumn(name = "user_id")
        @JsonBackReference
        private User user;

        // Default constructor
        public Address() {
        }

        // Parameterized constructor
        public Address(Long id, String value, User user) {
                this.id = id;
                this.value = value;
                this.user = user;
        }

        // Getters and setters
        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getValue() {
                return value;
        }

        public void setValue(String value) {
                this.value = value;
        }

        public User getUser() {
                return user;
        }

        public void setUser(User user) {
                this.user = user;
        }
}
