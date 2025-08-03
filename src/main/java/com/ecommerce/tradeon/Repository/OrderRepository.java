package com.ecommerce.tradeon.Repository;

import com.ecommerce.tradeon.Dto.Order.OrderDto;
import com.ecommerce.tradeon.Entity.Member.Member;
import com.ecommerce.tradeon.Entity.Order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order , Long>, SaleOrderRepository {

    List<Order> findByMemberId(Long memberId);

    @Query("select o from Order o where o.id = :orderId")
    Order findByOrderId(Long orderId);
}
