package com.ecommerce.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dtoRequest.CategoryRequestDto;
import com.ecommerce.dtoResponse.CategoryResponseDto;
import com.ecommerce.entity.Category;
import com.ecommerce.exception.CategoryAlreadyExistsException;
import com.ecommerce.exception.CategoryNotFoundException;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto) {

        if (categoryRepository.existsByCategoryName(categoryRequestDto.getCategoryName())) {
            throw new CategoryAlreadyExistsException(
                    "Category already exists with name: " + categoryRequestDto.getCategoryName());
        }

        Category category = new Category();

        category.setCategoryName(categoryRequestDto.getCategoryName());
        category.setCategoryDescription(categoryRequestDto.getCategoryDescription());

        Category savedCategory = categoryRepository.save(category);

        return mapToResponse(savedCategory);
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {

        List<Category> categoryList = categoryRepository.findAll();

        return categoryList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDto getCategoryById(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new CategoryNotFoundException("Category not found with id : " + categoryId));

        return mapToResponse(category);
    }

    @Override
    public CategoryResponseDto updateCategory(Long categoryId,
                                              CategoryRequestDto categoryRequestDto) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new CategoryNotFoundException("Category not found with id : " + categoryId));

        if (!category.getCategoryName().equalsIgnoreCase(categoryRequestDto.getCategoryName())
                && categoryRepository.existsByCategoryName(categoryRequestDto.getCategoryName())) {

            throw new CategoryAlreadyExistsException(
                    "Category already exists with name : "
                            + categoryRequestDto.getCategoryName());
        }

        category.setCategoryName(categoryRequestDto.getCategoryName());
        category.setCategoryDescription(categoryRequestDto.getCategoryDescription());

        Category updatedCategory = categoryRepository.save(category);

        return mapToResponse(updatedCategory);
    }

    @Override
    public String deleteCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new CategoryNotFoundException("Category not found with id : " + categoryId));

        categoryRepository.delete(category);

        return "Category deleted successfully.";
    }

    // Helper Method
    private CategoryResponseDto mapToResponse(Category category) {

        CategoryResponseDto response = new CategoryResponseDto();

        response.setCategoryId(category.getCategoryId());
        response.setCategoryName(category.getCategoryName());
        response.setCategoryDescription(category.getCategoryDescription());
        response.setCreatedAt(category.getCreatedAt());
        response.setUpdatedAt(category.getUpdatedAt());

        return response;
    }
}