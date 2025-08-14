package com.ecommerce.tradeon.Entity.Order;

import com.ecommerce.tradeon.Dto.Order.OrderItemOptionDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemOption {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String optionValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderitem_id")
    private OrderItem orderItem;

    public void addOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public void updateFromDto(OrderItemOptionDto dto) {
        name = dto.getName();
        optionValue = dto.getOptionValue();
    }
}
