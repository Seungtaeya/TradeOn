package com.ecommerce.tradeon.Repository;

import com.ecommerce.tradeon.Entity.Wishlist.WishListItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishListItemRepository extends JpaRepository<WishListItem, Long> {
    List<WishListItem> findByWishList_Id(Long wishListId);
}
