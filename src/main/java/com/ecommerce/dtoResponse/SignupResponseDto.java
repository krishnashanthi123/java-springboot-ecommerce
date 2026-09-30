package com.ecommerce.dtoResponse;

public class SignupResponseDto {

    private int sellerId;

    private String sellerUsername;

    private String email;

    private String role;

    private String message;

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int i) {
		this.sellerId = i;
	}

	public String getSellerUsername() {
		return sellerUsername;
	}

	public void setSellerUsername(String sellerUsername) {
		this.sellerUsername = sellerUsername;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

  
}