package com.Freshsync.freshsync_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Freshsync.freshsync_backend.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}