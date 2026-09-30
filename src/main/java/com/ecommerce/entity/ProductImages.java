package com.ecommerce.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
@Entity
public class ProductImages {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int imageId;

	    private String imageUrl;

	    
	    @JsonBackReference
	    @ManyToOne
	    @JoinColumn(name = "product_id")
	    private Products product;

		public int getImageId() {
			return imageId;
		}

		public void setImageId(int imageId) {
			this.imageId = imageId;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public Products getProduct() {
			return product;
		}

		public void setProduct(Products product) {
			this.product = product;
		}

	}


