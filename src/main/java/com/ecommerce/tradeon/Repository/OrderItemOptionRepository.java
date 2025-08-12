package com.ecommerce.tradeon.Repository;

import com.ecommerce.tradeon.Entity.Order.OrderItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemOptionRepository extends JpaRepository<OrderItemOption, Long> {
}
