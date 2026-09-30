package com.ecommerce.dtoResponse;

public class SellerResponseDto {

    private int sellerId;

    private String sellerUsername;

    private String sellerName;

    private String sellerAddress;
    
    private String message;

    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

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

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }
}