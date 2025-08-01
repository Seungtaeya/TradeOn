package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Entity.Cart.Cart;
import com.ecommerce.tradeon.Entity.Cart.CartItem;
import com.ecommerce.tradeon.Entity.product.Product;
import com.ecommerce.tradeon.Repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    @Transactional
    public CartItem createCartItem(Cart cart, Product product) {

        CartItem cartItem = new CartItem();
        cartItem.assignCart(cart);
        cartItem.assignProduct(product);

        return cartItemRepository.save(cartItem);
    }

    public CartItem getCartItemEntity(Long cartId) {
        return cartItemRepository.findCartIdByCartItem(cartId)
                .orElseThrow(() -> new IllegalArgumentException("해당 정보를 찾을 수 없습니다. getCartItemEntity"));
    }
    
    @Transactional
    public void CartItemRemove(Long cartId, Long itemProductId) {

        CartItem byProductId = cartItemRepository.findByCartItem_IdAndCart_Id(cartId,itemProductId)
                        .orElseThrow(() -> new IllegalArgumentException("해당 정보를 찾을 수 없습니다. CartItemRemove"));

        cartItemRepository.delete(byProductId);
    }
}
