package com.Freshsync.freshsync_backend.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private String brand;
    private String batchNo;
    private int quantity;
    private double price;
    private LocalDate manufacturingDate;
    private LocalDate expiryDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    public Product() {
    }

    public Product(Long id, String productName, String brand, String batchNo, int quantity, double price,
            LocalDate manufacturingDate, LocalDate expiryDate, Category category, Supplier supplier) {
        this.id = id;
        this.productName = productName;
        this.brand = brand;
        this.batchNo = batchNo;
        this.quantity = quantity;
        this.price = price;
        this.manufacturingDate = manufacturingDate;
        this.expiryDate = expiryDate;
        this.category = category;
        this.supplier = supplier;
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getBrand() {
        return brand;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public Category getCategory() {
        return category;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setManufacturingDate(LocalDate manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
    
    public int getRemainingStock() {
        return this.quantity;
    }
    
    public String getStockStatus() {
        if (this.quantity == 0) {
            return "Out of Stock";
        } else if (this.quantity <= 5) {
            return "Low Stock";
        } else {
            return "Available";
        }
    }
    
    public Long getDaysToExpire() {
        if (this.expiryDate == null) {
            return null;
        }

        LocalDate today = LocalDate.now();
        long daysLeft = ChronoUnit.DAYS.between(today, this.expiryDate);

        if (daysLeft < 0) {
            return null;
        }

        return daysLeft;
    }

    public String getExpiryStatus() {
        if (this.expiryDate == null) {
            return "Expiry Date Not Available";
        }

        LocalDate today = LocalDate.now();
        long daysLeft = ChronoUnit.DAYS.between(today, this.expiryDate);

        if (daysLeft < 0) {
            return "Expired";
        } else if (daysLeft == 0) {
            return "Expiring Today";
        } else if (daysLeft <= 7) {
            return "Expiring Soon";
        } else {
            return "Safe";
        }
      
    }
}