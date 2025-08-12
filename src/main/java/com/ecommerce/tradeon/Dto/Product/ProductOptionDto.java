package com.ecommerce.tradeon.Dto.Product;

import com.ecommerce.tradeon.Entity.product.ProductOption;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductOptionDto {

    private Long id;
    private Long productId;
    private String name;
    private String optionValue;
    private List<String> values;

    public static ProductOptionDto setForm(ProductOption option) {
        ProductOptionDto dto = new ProductOptionDto();

        dto.setId(option.getId());
        dto.setProductId(option.getProduct().getId());
        dto.setName(option.getName());
        dto.setOptionValue(option.getOptionValue());
        return dto;
    }
}
