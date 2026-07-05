package com.Freshsync.freshsync_backend.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Freshsync.freshsync_backend.model.Product;
import com.Freshsync.freshsync_backend.repository.CategoryRepository;
import com.Freshsync.freshsync_backend.repository.ProductRepository;
import com.Freshsync.freshsync_backend.repository.SupplierRepository;

@Service
public class DashboardService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    public Map<String, Long> getDashboardCounts() {

        List<Product> products = productRepository.findAll();

        long totalProducts = productRepository.count();
        long totalCategories = categoryRepository.count();
        long totalSuppliers = supplierRepository.count();

        long lowStockProducts = products.stream()
                .filter(product -> product.getQuantity() > 0 && product.getQuantity() <= 5)
                .count();

        long outOfStockProducts = products.stream()
                .filter(product -> product.getQuantity() == 0)
                .count();

        long expiredProducts = products.stream()
                .filter(product -> product.getExpiryDate() != null &&
                        product.getExpiryDate().isBefore(LocalDate.now()))
                .count();

        long expiringSoonProducts = products.stream()
                .filter(product -> {
                    if (product.getExpiryDate() == null) {
                        return false;
                    }

                    long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), product.getExpiryDate());
                    return daysLeft >= 0 && daysLeft <= 7;
                })
                .count();

        Map<String, Long> dashboard = new HashMap<>();
        dashboard.put("totalProducts", totalProducts);
        dashboard.put("totalCategories", totalCategories);
        dashboard.put("totalSuppliers", totalSuppliers);
        dashboard.put("lowStockProducts", lowStockProducts);
        dashboard.put("outOfStockProducts", outOfStockProducts);
        dashboard.put("expiredProducts", expiredProducts);
        dashboard.put("expiringSoonProducts", expiringSoonProducts);

        return dashboard;
    }
}