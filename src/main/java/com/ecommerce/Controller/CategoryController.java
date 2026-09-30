package com.ecommerce.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.dtoRequest.CategoryRequestDto;
import com.ecommerce.dtoResponse.CategoryResponseDto;
import com.ecommerce.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Add Category
    @PostMapping
    public ResponseEntity<CategoryResponseDto> addCategory(
            @Valid @RequestBody CategoryRequestDto categoryRequestDto) {

        CategoryResponseDto response = categoryService.addCategory(categoryRequestDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get All Categories
    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {

        List<CategoryResponseDto> response = categoryService.getAllCategories();

        return ResponseEntity.ok(response);
    }

    // Get Category By Id
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(
            @PathVariable Long categoryId) {

        CategoryResponseDto response = categoryService.getCategoryById(categoryId);

        return ResponseEntity.ok(response);
    }

    // Update Category
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDto> updateCategory(
            @PathVariable Long categoryId,
            @Valid @RequestBody CategoryRequestDto categoryRequestDto) {

        CategoryResponseDto response =
                categoryService.updateCategory(categoryId, categoryRequestDto);

        return ResponseEntity.ok(response);
    }

    // Delete Category
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(
            @PathVariable Long categoryId) {

        String response = categoryService.deleteCategory(categoryId);

        return ResponseEntity.ok(response);
    }
}