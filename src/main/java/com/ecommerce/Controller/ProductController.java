package com.ecommerce.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.dtoRequest.ProductRequestDto;
import com.ecommerce.dtoResponse.ProductResponseDto;
import com.ecommerce.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // ADD PRODUCT
    @PostMapping
    public ResponseEntity<ProductResponseDto> addProduct(
            @Valid @RequestBody ProductRequestDto dto) {

        ProductResponseDto response = productService.addProduct(dto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // GET ALL PRODUCTS
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {

        return ResponseEntity.ok(productService.getAllProducts());
    }

    // GET PRODUCT BY ID
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> getProductById(
            @PathVariable Integer productId) {

        return ResponseEntity.ok(productService.getProductById(productId));
    }

    // UPDATE PRODUCT
    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable Integer productId,
            @Valid @RequestBody ProductRequestDto dto) {

        ProductResponseDto response =
                productService.updateProduct(productId, dto);

        return ResponseEntity.ok(response);
    }

    // DELETE PRODUCT
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Integer productId) {

        productService.deleteProduct(productId);

        return ResponseEntity.ok("Product Deleted Successfully");
    }

    // ADD TAG TO PRODUCT
    @PostMapping("/{productId}/tags/{tagId}")
    public ResponseEntity<String> addTagToProduct(
            @PathVariable Integer productId,
            @PathVariable Integer tagId) {

        return ResponseEntity.ok(
                productService.addTagToProduct(productId, tagId));
    }

    // REMOVE TAG FROM PRODUCT
    @DeleteMapping("/{productId}/tags/{tagId}")
    public ResponseEntity<String> removeTagFromProduct(
            @PathVariable Integer productId,
            @PathVariable Integer tagId) {

        return ResponseEntity.ok(
                productService.removeTagFromProduct(productId, tagId));
    }
}