package com.ecommerce.tradeon.Dto.Order;

import com.ecommerce.tradeon.Dto.Product.ProductDto;
import com.ecommerce.tradeon.Entity.Order.OrderItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderItemDto {

    private Long memberId;
    private Long productId;
    private ProductDto productDto;
    private List<OrderItemOptionDto> optionDtos;

    public static OrderItemDto setForm(OrderItem orderItem) {
        OrderItemDto dto = new OrderItemDto();

        dto.setMemberId(orderItem.getOrder().getMember().getId());
        dto.setProductId(orderItem.getProduct().getId());
        dto.setProductDto(ProductDto.setForm(orderItem.getProduct()));
        dto.setOptionDtos(orderItem.getOption().stream()
                .map(OrderItemOptionDto::setForm)
                .toList());

        return dto;
    }
}