package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dtoRequest.CustomerLoginRequestDto;
import com.ecommerce.dtoResponse.CustomerLoginResponseDto;

public interface CustomerService {

    // Register Customer
    CustomerLoginResponseDto registerCustomer(CustomerLoginRequestDto dto);

    // Customer Login
    CustomerLoginResponseDto login(CustomerLoginRequestDto dto);

    // Get All Customers
    List<CustomerLoginResponseDto> getAllCustomers();

    // Get Customer By Id
    CustomerLoginResponseDto getCustomerById(Long customerId);

    // Update Customer
    CustomerLoginResponseDto updateCustomer(Long customerId,
                                       CustomerLoginRequestDto dto);

    // Delete Customer
    String deleteCustomer(Long customerId);
}