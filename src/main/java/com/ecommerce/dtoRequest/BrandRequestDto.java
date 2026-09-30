package com.ecommerce.dtoRequest;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BrandRequestDto {

    @NotBlank(message = "Brand name is required")
    @Size(min = 2, max = 100, message = "Brand name should be between 2 and 100 characters")
    private String brandName;

    @NotBlank(message = "Brand description is required")
    @Size(max = 500, message = "Brand description should not exceed 500 characters")
    private String brandDescription;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandDescription() {
        return brandDescription;
    }

    public void setBrandDescription(String brandDescription) {
        this.brandDescription = brandDescription;
    }
}