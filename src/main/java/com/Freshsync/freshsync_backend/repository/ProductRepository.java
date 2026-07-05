package com.Freshsync.freshsync_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Freshsync.freshsync_backend.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductNameContainingIgnoreCase(String productName);

    List<Product> findByBrandContainingIgnoreCase(String brand);

    List<Product> findByBatchNoContainingIgnoreCase(String batchNo);
}