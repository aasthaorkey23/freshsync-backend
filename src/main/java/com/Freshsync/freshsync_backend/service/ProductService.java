package com.Freshsync.freshsync_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Freshsync.freshsync_backend.model.Product;
import com.Freshsync.freshsync_backend.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id).orElse(null);

        if (product != null) {
            product.setProductName(productDetails.getProductName());
            product.setBrand(productDetails.getBrand());
            product.setBatchNo(productDetails.getBatchNo());
            product.setQuantity(productDetails.getQuantity());
            product.setPrice(productDetails.getPrice());
            product.setManufacturingDate(productDetails.getManufacturingDate());
            product.setExpiryDate(productDetails.getExpiryDate());
            product.setCategory(productDetails.getCategory());
            product.setSupplier(productDetails.getSupplier());

            return productRepository.save(product);
        }

        return null;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    
    public List<Product> searchProducts(String keyword) {
        List<Product> productsByName = productRepository.findByProductNameContainingIgnoreCase(keyword);

        if (!productsByName.isEmpty()) {
            return productsByName;
        }

        List<Product> productsByBrand = productRepository.findByBrandContainingIgnoreCase(keyword);

        if (!productsByBrand.isEmpty()) {
            return productsByBrand;
        }

        return productRepository.findByBatchNoContainingIgnoreCase(keyword);
    }
}