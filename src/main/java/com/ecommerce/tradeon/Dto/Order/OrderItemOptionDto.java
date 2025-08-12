package com.ecommerce.tradeon.Dto.Order;

import com.ecommerce.tradeon.Entity.Order.OrderItemOption;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class OrderItemOptionDto {

    private Long id;
    private String name;
    private String optionValue;

    public static OrderItemOptionDto setForm(OrderItemOption option) {
        OrderItemOptionDto dto = new OrderItemOptionDto();
        dto.setId(option.getId());
        dto.setName(option.getName());
        dto.setOptionValue(option.getOptionValue());

        return dto;
    }
}
