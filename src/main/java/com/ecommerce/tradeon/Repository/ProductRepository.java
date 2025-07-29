package com.ecommerce.tradeon.Repository;

import com.ecommerce.tradeon.Entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
