package com.minimarket.web.service.impl;

import com.minimarket.web.dto.request.CategoryRequest;
import com.minimarket.web.dto.response.CategoryResponse;
import com.minimarket.web.model.product.Category;
import com.minimarket.web.repository.CategoryRepository;
import com.minimarket.web.service.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> new CategoryResponse(category.getId(), category.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse addCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        Category savedCategory = categoryRepository.save(category);
        return new CategoryResponse(savedCategory.getId(), savedCategory.getName());
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with ID: " + id);
        }

        categoryRepository.deleteById(id);

        // Jika tabel kosong, reset auto_increment
        if (categoryRepository.count() == 0) {
            jdbcTemplate.execute("ALTER TABLE category AUTO_INCREMENT = 1");
        }
    }


    @Override
    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));
        return new CategoryResponse(category.getId(), category.getName());
    }

    @Override
    public void updateCategory(Long id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));
        category.setName(categoryRequest.getName());
        categoryRepository.save(category);
    }

}
