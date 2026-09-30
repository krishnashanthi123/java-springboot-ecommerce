package com.ecommerce.repository;

import com.ecommerce.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoRepository extends JpaRepository<SellerInfo, Integer> {
}