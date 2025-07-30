package com.ecommerce.tradeon.Repository;

import com.ecommerce.tradeon.Entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.seller_id.id = :memberId")
    List<Product> findBySeller_id(Long memberId);
}
