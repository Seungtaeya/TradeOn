package com.ecommerce.tradeon.Dto.Order;

import com.ecommerce.tradeon.Entity.Order.Order;
import com.ecommerce.tradeon.Entity.Order.OrderItem;
import com.ecommerce.tradeon.Enums.OrderStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class SalesOrderDto {

    private Long id;
    private List<OrderItem> items;
    private OrderStatus status;
    private LocalDateTime createdAt;

    @QueryProjection
    public SalesOrderDto(Long id, List<OrderItem> items, OrderStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.items = items;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static SalesOrderDto setForm(Order order) {
        SalesOrderDto dto = new SalesOrderDto();
        dto.setId(order.getId());
        dto.setItems(order.getOrderItems());
        dto.setStatus(order.getOrderStatus());
        dto.setCreatedAt(order.getOrderDate());

        return dto;
    }
}
