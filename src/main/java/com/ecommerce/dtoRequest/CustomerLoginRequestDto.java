package com.ecommerce.dtoRequest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CustomerLoginRequestDto {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String customerEmail;

    @NotBlank(message = "Password is required")
    private String customerPassword;

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }
}