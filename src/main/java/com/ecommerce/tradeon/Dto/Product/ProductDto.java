package com.ecommerce.tradeon.Dto.Product;

import com.ecommerce.tradeon.Entity.Image.ProductImage;
import com.ecommerce.tradeon.Entity.product.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private Long seller_id;
    private Long category_id;
    private List<ProductImageDto> imageUrl;
    private String title;
    private String description;
    private int price;
    private int stock;
    private Boolean isUsed;

    public static ProductDto setForm(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        List<ProductImageDto> images = product.getImages().stream()
                .map(ProductImageDto::setForm)
                .toList();

        productDto.setImageUrl(images);
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setStock(product.getStock());
        productDto.setIsUsed(product.isUsed());
        return productDto;
    }

    public ProductDto(Long seller_id, Long category_id, List<ProductImageDto> imageUrl, String title, String description, int price, int stock, Boolean isUsed) {
        this.seller_id = seller_id;
        this.category_id = category_id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.isUsed = isUsed;
    }
}
