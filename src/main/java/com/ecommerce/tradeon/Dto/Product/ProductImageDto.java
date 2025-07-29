package com.ecommerce.tradeon.Dto.Product;

import com.ecommerce.tradeon.Entity.Image.ProductImage;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductImageDto {

    private Long id;
    private String url;


    public static ProductImageDto setForm(ProductImage image) {
        ProductImageDto productImageDto = new ProductImageDto();
        productImageDto.setId(image.getId());
        productImageDto.setUrl(image.getImageUrl());

        return productImageDto;
    }
}
