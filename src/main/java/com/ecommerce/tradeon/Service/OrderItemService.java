package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Entity.Cart.CartItem;
import com.ecommerce.tradeon.Entity.Order.Order;
import com.ecommerce.tradeon.Entity.Order.OrderItem;
import com.ecommerce.tradeon.Entity.product.Product;
import com.ecommerce.tradeon.Repository.OrderitemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderitemRepository orderitemRepository;

    @Transactional
    public void createOrderItem(Order order, Product product) {

        if(order == null || product == null) {
            throw new IllegalArgumentException("주문 오류입니다 ");
        }

        OrderItem orderItem = new OrderItem();

        orderItem.assignOrder(order);
        orderItem.assignProduct(product);
        orderItem.addPrice(product.getPrice());
        orderItem.deleteQuantity();

        orderitemRepository.save(orderItem);
    }

    public List<OrderItem> findOrderItems(Long memberId) {
        return orderitemRepository.findByProductSeller_id(memberId);
    }
}
