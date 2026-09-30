package com.ecommerce.entity;

import java.util.List;

import com.ecommerce.dtoResponse.ProductResponseDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "seller")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private int sellerId;

    @Column(name = "seller_username")
    private String sellerUsername;

    @Column(name = "seller_password")
    private String sellerPassword;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private String role;

    @OneToOne
    @JoinColumn(name = "sellerinfo_id")
    private SellerInfo sellerInfo;

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerUsername() {
		return sellerUsername;
	}

	public void setSellerUsername(String sellerUsername) {
		this.sellerUsername = sellerUsername;
	}

	public String getSellerPassword() {
		return sellerPassword;
	}

	public void setSellerPassword(String sellerPassword) {
		this.sellerPassword = sellerPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public SellerInfo getSellerInfo() {
		return sellerInfo;
	}

	public void setSellerInfo(SellerInfo sellerInfo) {
		this.sellerInfo = sellerInfo;
	}

    
}

