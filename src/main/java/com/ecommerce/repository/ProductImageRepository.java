package com.ecommerce.repository;

import com.ecommerce.entity.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImages,Integer> {

   

}