package com.ecommerce.tradeon.Dto.Order;

import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class OrderRequestDto {

    private Long productId;
    private Map<String,String> selectedOptions = new HashMap<>();
}
