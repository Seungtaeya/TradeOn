package com.ecommerce.tradeon.Dto.Order;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderStatusDto {

    private Long orderId;
    private String status;
}
