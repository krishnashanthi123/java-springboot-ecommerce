package com.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	boolean existsByCustomerEmail(String customerEmail);
	
	boolean existsByCustomerMobile(String customerMobile);
	
	Optional<Customer> findByCustomerEmail(String customerEmail);

}
