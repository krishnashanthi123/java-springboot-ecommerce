package com.ecommerce.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dtoRequest.BrandRequestDto;
import com.ecommerce.dtoResponse.BrandResponseDto;
import com.ecommerce.entity.Brand;
import com.ecommerce.exception.BrandAlreadyExistsException;
import com.ecommerce.exception.BrandNotFoundException;
import com.ecommerce.repository.BrandRepository;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    // ===========================
    // ADD BRAND
    // ===========================
    public BrandResponseDto addBrand(BrandRequestDto dto) {

        if (brandRepository.existsByBrandName(dto.getBrandName())) {
            throw new BrandAlreadyExistsException(
                    "Brand already exists with name : " + dto.getBrandName());
        }

        Brand brand = new Brand();

        brand.setBrandName(dto.getBrandName());
        brand.setBrandDescription(dto.getBrandDescription());

        Brand savedBrand = brandRepository.save(brand);

        return mapToResponse(savedBrand);
    }

    // ===========================
    // GET ALL BRANDS
    // ===========================
    public List<BrandResponseDto> getAllBrands() {

        return brandRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ===========================
    // GET BRAND BY ID
    // ===========================
    public BrandResponseDto getBrandById(Long brandId) {

        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() ->
                        new BrandNotFoundException(
                                "Brand not found with id : " + brandId));

        return mapToResponse(brand);
    }

    // ===========================
    // UPDATE BRAND
    // ===========================
    public BrandResponseDto updateBrand(
            Long brandId,
            BrandRequestDto dto) {

        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() ->
                        new BrandNotFoundException(
                                "Brand not found with id : " + brandId));

        if (!brand.getBrandName().equalsIgnoreCase(dto.getBrandName())
                && brandRepository.existsByBrandName(dto.getBrandName())) {

            throw new BrandAlreadyExistsException(
                    "Brand already exists with name : " + dto.getBrandName());
        }

        brand.setBrandName(dto.getBrandName());
        brand.setBrandDescription(dto.getBrandDescription());

        Brand updatedBrand = brandRepository.save(brand);

        return mapToResponse(updatedBrand);
    }

    // ===========================
    // DELETE BRAND
    // ===========================
    public String deleteBrand(Long brandId) {

        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() ->
                        new BrandNotFoundException(
                                "Brand not found with id : " + brandId));

        brandRepository.delete(brand);

        return "Brand deleted successfully.";
    }

    // ===========================
    // ENTITY -> DTO
    // ===========================
    private BrandResponseDto mapToResponse(Brand brand) {

        BrandResponseDto response = new BrandResponseDto();

        response.setBrandId(brand.getBrandId());
        response.setBrandName(brand.getBrandName());
        response.setBrandDescription(brand.getBrandDescription());
        response.setCreatedAt(brand.getCreatedAt());
        response.setUpdatedAt(brand.getUpdatedAt());

        return response;
    }
}
