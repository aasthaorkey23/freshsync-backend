package com.Freshsync.freshsync_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Freshsync.freshsync_backend.model.Company;
import com.Freshsync.freshsync_backend.repository.CompanyRepository;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    public Company updateCompany(Long id, Company companyDetails) {
        Company company = companyRepository.findById(id).orElse(null);

        if (company != null) {
            company.setCompanyName(companyDetails.getCompanyName());
            company.setEmail(companyDetails.getEmail());
            company.setPhone(companyDetails.getPhone());
            company.setAddress(companyDetails.getAddress());

            return companyRepository.save(company);
        }

        return null;
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}