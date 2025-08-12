package com.ecommerce.tradeon.Repository;

import com.ecommerce.tradeon.Entity.product.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
}
