package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dtoRequest.CategoryRequestDto;
import com.ecommerce.dtoResponse.CategoryResponseDto;

public interface CategoryService {

    // Add Category
    CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto);

    // Get All Categories
    List<CategoryResponseDto> getAllCategories();

    // Get Category By Id
    CategoryResponseDto getCategoryById(Long categoryId);

    // Update Category
    CategoryResponseDto updateCategory(Long categoryId, CategoryRequestDto categoryRequestDto);

    // Delete Category
    String deleteCategory(Long categoryId);

}