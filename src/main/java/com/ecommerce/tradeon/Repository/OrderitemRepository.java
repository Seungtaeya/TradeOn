package com.ecommerce.tradeon.Repository;

import com.ecommerce.tradeon.Entity.Order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderitemRepository extends JpaRepository<OrderItem, Long> {

    @Query("select oi from OrderItem oi where oi.product.seller_id = :memberId")
    List<OrderItem> findByProductSeller_id(Long memberId);

}
