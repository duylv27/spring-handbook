package com.spring.handbook.data.repository;

import com.spring.handbook.data.entity.User;
import com.spring.handbook.data.repository.projection.UserProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = {"address", "roles", "posts"})
    List<User> findAll();

    @EntityGraph(attributePaths = {"address", "roles", "posts"})
    Optional<User> findById(Long id);

}
