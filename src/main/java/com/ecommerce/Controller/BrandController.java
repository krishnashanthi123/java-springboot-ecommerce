package com.ecommerce.Controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.dtoRequest.BrandRequestDto;
import com.ecommerce.dtoResponse.BrandResponseDto;
import com.ecommerce.service.BrandService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/brand")
@Validated
public class BrandController {

    @Autowired
    private BrandService brandService;

    // ==========================
    // ADD BRAND
    // ==========================
    @PostMapping
    public ResponseEntity<BrandResponseDto> addBrand(
            @Valid @RequestBody BrandRequestDto brandRequestDto) {

        BrandResponseDto response = brandService.addBrand(brandRequestDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // ==========================
    // GET ALL BRANDS
    // ==========================
    @GetMapping
    public ResponseEntity<List<BrandResponseDto>> getAllBrands() {

        List<BrandResponseDto> response = brandService.getAllBrands();

        return ResponseEntity.ok(response);
    }

    // ==========================
    // GET BRAND BY ID
    // ==========================
    @GetMapping("/{brandId}")
    public ResponseEntity<BrandResponseDto> getBrandById(
            @PathVariable Long brandId) {

        BrandResponseDto response = brandService.getBrandById(brandId);

        return ResponseEntity.ok(response);
    }

    // ==========================
    // UPDATE BRAND
    // ==========================
    @PutMapping("/{brandId}")
    public ResponseEntity<BrandResponseDto> updateBrand(
            @PathVariable Long brandId,
            @Valid @RequestBody BrandRequestDto brandRequestDto) {

        BrandResponseDto response =
                brandService.updateBrand(brandId, brandRequestDto);

        return ResponseEntity.ok(response);
    }

    // ==========================
    // DELETE BRAND
    // ==========================
    @DeleteMapping("/{brandId}")
    public ResponseEntity<String> deleteBrand(
            @PathVariable Long brandId) {

        String response = brandService.deleteBrand(brandId);

        return ResponseEntity.ok(response);
    }
}