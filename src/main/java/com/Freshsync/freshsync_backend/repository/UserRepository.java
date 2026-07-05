package com.Freshsync.freshsync_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Freshsync.freshsync_backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);

    List<User> findByRoleIgnoreCase(String role);
}