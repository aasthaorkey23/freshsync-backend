package com.Freshsync.freshsync_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Freshsync.freshsync_backend.model.Category;
import com.Freshsync.freshsync_backend.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category updateCategory(Long id, Category categoryDetails) {
        Category category = categoryRepository.findById(id).orElse(null);

        if (category != null) {
            category.setCategoryName(categoryDetails.getCategoryName());
            category.setDescription(categoryDetails.getDescription());

            return categoryRepository.save(category);
        }

        return null;
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}