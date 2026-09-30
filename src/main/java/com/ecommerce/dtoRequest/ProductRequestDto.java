package com.ecommerce.dtoRequest;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProductRequestDto {

    @NotBlank(message = "Product name is required")
    private String productName;

    @NotBlank(message = "Product description is required")
    private String productDescription;

    @NotNull(message = "Product quantity is required")
    @Min(value = 1, message = "Product quantity must be at least 1")
    private Integer productQuantity;

    @NotNull(message = "Product MRP price is required")
    @Positive(message = "Product MRP price must be greater than 0")
    private Double productMrpPrice;

    @NotNull(message = "Product selling price is required")
    @Positive(message = "Product selling price must be greater than 0")
    private Double productSellingPrice;

    @NotNull(message = "Returnable field is required")
    private Boolean returnable;

    @NotNull(message = "Refundable field is required")
    private Boolean refundable;

    @NotNull(message = "Seller ID is required")
    @Positive(message = "Seller ID must be greater than 0")
    
    
    @NotNull(message = "Brand Id is required")
    private Long brandId;
    
    private Integer sellerId;

    
    private Long categoryId;
    
    // Getters and Setters

    public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Double getProductMrpPrice() {
        return productMrpPrice;
    }

    public void setProductMrpPrice(Double productMrpPrice) {
        this.productMrpPrice = productMrpPrice;
    }

    public Double getProductSellingPrice() {
        return productSellingPrice;
    }

    public void setProductSellingPrice(Double productSellingPrice) {
        this.productSellingPrice = productSellingPrice;
    }

    public Boolean getReturnable() {
        return returnable;
    }

    public void setReturnable(Boolean returnable) {
        this.returnable = returnable;
    }

    public Boolean getRefundable() {
        return refundable;
    }

    public void setRefundable(Boolean refundable) {
        this.refundable = refundable;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	
}