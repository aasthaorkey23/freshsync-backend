package com.Freshsync.freshsync_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Freshsync.freshsync_backend.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}