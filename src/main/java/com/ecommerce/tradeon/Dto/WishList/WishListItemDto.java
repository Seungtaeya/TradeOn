package com.ecommerce.tradeon.Dto.WishList;

import com.ecommerce.tradeon.Dto.Product.ProductDto;
import com.ecommerce.tradeon.Entity.Wishlist.WishListItem;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WishListItemDto {

    private Long id;
    private Long wishListId;
    private ProductDto productDto;

    public static WishListItemDto setForm(WishListItem item) {
        WishListItemDto dto = new WishListItemDto();
        dto.setId(item.getId());
        dto.setWishListId(item.getWishList().getId());
        dto.setProductDto(ProductDto.setWishForm(item));

        return dto;
    }
}
