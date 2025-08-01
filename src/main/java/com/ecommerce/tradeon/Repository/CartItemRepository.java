package com.ecommerce.tradeon.Repository;

import com.ecommerce.tradeon.Entity.Cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("select ci from CartItem ci where ci.cart.id = :cartId")
    Optional<CartItem> findCartIdByCartItem(Long cartId);


    @Query("select ci from CartItem ci where  ci.cart.id = :cartId and ci.id = :itemProductId")
    Optional<CartItem> findByCartItem_IdAndCart_Id(Long cartId, Long itemProductId);
}
