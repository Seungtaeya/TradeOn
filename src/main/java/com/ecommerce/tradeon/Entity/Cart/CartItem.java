package com.ecommerce.tradeon.Entity.Cart;

import com.ecommerce.tradeon.Entity.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class CartItem {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public void assignCart(Cart cart) {
        this.cart = cart;
        cart.getItems().add(this);
    }

    public void assignProduct(Product product) {
        this.product = product;
        product.getCart().add(this);
    }
}
