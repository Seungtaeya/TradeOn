package com.ecommerce.tradeon.Repository;

import com.ecommerce.tradeon.Entity.Image.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
