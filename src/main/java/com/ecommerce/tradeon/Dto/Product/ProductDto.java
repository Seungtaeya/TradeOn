package com.ecommerce.tradeon.Dto.Product;

import com.ecommerce.tradeon.Dto.Order.OrderRequestDto;
import com.ecommerce.tradeon.Dto.Vo.RegionDto;
import com.ecommerce.tradeon.Entity.Wishlist.WishListItem;
import com.ecommerce.tradeon.Entity.product.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private Long seller_id;
    private Long category_id;
    private String categoryName;
    private List<ProductImageDto> imageUrl;
    private List<ProductOptionDto> options;
    private OrderRequestDto orderRequestDto;
    private String title;
    private String description;
    private int price;
    private int stock;
    private Long viewCount;
    private Boolean isUsed;
    private LocalDateTime createAt;
    private RegionDto region;

    public static ProductDto setForm(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setSeller_id(product.getSeller_id().getId());
        productDto.setCategory_id(product.getCategory().getId());
        productDto.setCategoryName(product.getCategory().getName());
        List<ProductImageDto> images = product.getImages().stream()
                .map(ProductImageDto::setForm)
                .toList();
        List<ProductOptionDto> options = product.getOptions().stream()
                .map(ProductOptionDto::setForm)
                .toList();
        productDto.setImageUrl(images);
        productDto.setOptions(options);
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setStock(product.getStock());
        productDto.setViewCount(product.getViewCount());
        productDto.setIsUsed(product.isUsed());
        productDto.setCreateAt(product.getCreate_At());
        if(product.getRegion() != null) {
            productDto.setRegion(new RegionDto(product.getRegion().getSido(), product.getRegion().getSigun(), product.getRegion().getDong()));
        }
        return productDto;
    }

    public static ProductDto setWishForm(WishListItem item) {
        ProductDto productDto = new ProductDto();
        productDto.setId(item.getProduct().getId());
        productDto.setSeller_id(item.getProduct().getSeller_id().getId());
        productDto.setCategory_id(item.getProduct().getCategory().getId());
        productDto.setCategoryName(item.getProduct().getCategory().getName());
        List<ProductImageDto> images = item.getProduct().getImages().stream()
                .map(ProductImageDto::setForm)
                .toList();
        productDto.setImageUrl(images);
        productDto.setTitle(item.getProduct().getTitle());
        productDto.setDescription(item.getProduct().getDescription());
        productDto.setPrice(item.getProduct().getPrice());
        productDto.setStock(item.getProduct().getStock());
        productDto.setIsUsed(item.getProduct().isUsed());
        productDto.setCreateAt(item.getProduct().getCreate_At());
        if(item.getProduct().getRegion() != null) {
            productDto.setRegion(new RegionDto(item.getProduct().getRegion().getSido(), item.getProduct().getRegion().getSigun(), item.getProduct().getRegion().getDong()));
        }
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
