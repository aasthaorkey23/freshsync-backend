package com.Freshsync.freshsync_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Freshsync.freshsync_backend.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}