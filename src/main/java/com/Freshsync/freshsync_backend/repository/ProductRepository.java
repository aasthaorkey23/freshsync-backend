package com.Freshsync.freshsync_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Freshsync.freshsync_backend.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}