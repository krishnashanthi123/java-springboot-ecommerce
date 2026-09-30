package com.ecommerce.repository;

import com.ecommerce.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Integer> {


   Optional<Seller> findByEmail(String email);

    Optional<Seller> findBySellerUsername(String sellerUsername);


 
}