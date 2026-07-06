package com.Freshsync.freshsync_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Freshsync.freshsync_backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByRoleIgnoreCase(String role);
    
    User findByEmail(String email);
}	