package com.ecommerce.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    private String productName;

    private String productDescription;

    private Integer productQuantity;

    private Double productMrpPrice;

    private Double productSellingPrice;

    private Boolean returnable;

    private Boolean refundable;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

  //Infinite recursion (StackOverflowError)
    @JsonManagedReference
    @OneToMany(mappedBy = "product")
    private List<ProductImages> images;

    @ManyToMany
    @JoinTable(
        name = "product_tags_mapping",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    
    private List<Tags> tags;
    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    
    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;
    

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public List<ProductImages> getImages() {
		return images;
	}

	public void setImages(List<ProductImages> images) {
		this.images = images;
	}

	public List<Tags> getTags() {
		return tags;
	}

	public void setTags(List<Tags> tags) {
		this.tags = tags;
	}
	public Brand getBrand() {
	    return brand;
	}

	public void setBrand(Brand brand) {
	    this.brand = brand;
	}

	

    // getters and setters
}