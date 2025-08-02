package com.ecommerce.tradeon.Dto.Order;

import com.ecommerce.tradeon.Entity.Order.Order;
import com.ecommerce.tradeon.Entity.Order.OrderItem;
import com.ecommerce.tradeon.Enums.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderDetailDto {

    private Long id;
    private OrderStatus status;
    private int TotalPrice;
    private List<OrderItem> items;

    public static OrderDetailDto setForm(Order order) {
        OrderDetailDto dto = new OrderDetailDto();
        dto.setId(order.getId());
        dto.setStatus(order.getOrderStatus());
        dto.setItems(order.getOrderItems());
        dto.setTotalPrice(order.getOrderItems().stream().mapToInt(OrderItem::getPrice).sum());

        return dto;
    }
}
