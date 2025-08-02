package com.ecommerce.tradeon.Dto.Order;

import com.ecommerce.tradeon.Entity.Order.Order;
import com.ecommerce.tradeon.Entity.Order.OrderItem;
import com.ecommerce.tradeon.Entity.product.Product;
import com.ecommerce.tradeon.Enums.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private LocalDateTime orderDate;
    private List<OrderItem> orderItems;
    private OrderStatus orderStatus;
    private int totalPrice;

    public static OrderDto setForm(Order order) {
        OrderDto Dto = new OrderDto();
        Dto.setId(order.getId());
        Dto.setOrderDate(order.getOrderDate());
        Dto.setOrderItems(order.getOrderItems());
        Dto.setOrderStatus(order.getOrderStatus());
        Dto.setTotalPrice(order.getOrderItems().stream()
                .mapToInt(OrderItem::getPrice)
                .sum());

        return Dto;
    }
}
