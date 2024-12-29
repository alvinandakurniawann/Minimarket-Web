package com.minimarket.web.service.interfaces;

import com.minimarket.web.dto.request.CategoryRequest;
import com.minimarket.web.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategories();
    CategoryResponse addCategory(CategoryRequest categoryRequest);
    void deleteCategory(Long id);
    CategoryResponse getCategoryById(Long id);
    void updateCategory(Long id, CategoryRequest categoryRequest);
}
