package com.ecommerce.tradeon.Dto.Order;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDto {

    private Long memberId;
    private Long productId;

}
