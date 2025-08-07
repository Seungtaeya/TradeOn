package com.ecommerce.tradeon.Entity.Wishlist;

import com.ecommerce.tradeon.Entity.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class WishListItem {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wishList_id")
    private WishList wishList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public void addWishList(WishList wishList) {
        if(wishList != null) {
            this.wishList = wishList;
            wishList.getItemLIst().add(this);
        }
    }

    public void addProduct(Product product) {
        this.product = product;
    }
}
