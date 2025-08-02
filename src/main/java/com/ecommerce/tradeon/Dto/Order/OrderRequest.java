package com.ecommerce.tradeon.Dto.Order;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderRequest {

    private Long productId;

    private boolean ordercart;

}
