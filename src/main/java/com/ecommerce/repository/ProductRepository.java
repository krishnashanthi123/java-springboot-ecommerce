/*package com.ecommerce.repository;*/

package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.entity.Products;

public interface ProductRepository extends JpaRepository<Products, Integer> {
}