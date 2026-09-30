package com.ecommerce.dtoRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.URL;

public class ProductImageRequestDto {

    @NotBlank(message = "Image URL is required")
    @URL(message = "Please provide a valid image URL")
    private String imageUrl;

    @NotNull(message = "Product ID is required")
    @Positive(message = "Product ID must be greater than 0")
    private Long productId;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}