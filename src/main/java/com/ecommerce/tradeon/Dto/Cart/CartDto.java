package com.ecommerce.tradeon.Dto.Cart;

import com.ecommerce.tradeon.Dto.Product.ProductDto;
import com.ecommerce.tradeon.Entity.Cart.Cart;
import com.ecommerce.tradeon.Entity.Cart.CartItem;
import com.ecommerce.tradeon.Entity.product.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CartDto {

    private Long id;
    private Long memberId;
    private Long productId;
    private List<CartItem> items;
    private int price;

    public static CartDto setForm(Cart cart) {
        CartDto dto = new CartDto();
        dto.setId(cart.getId());
        dto.setMemberId(cart.getMember().getId());
        dto.setItems(cart.getItems());
        return dto;
    }

}
