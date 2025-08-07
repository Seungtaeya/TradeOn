package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Dto.WishList.WishListItemDto;
import com.ecommerce.tradeon.Entity.Wishlist.WishList;
import com.ecommerce.tradeon.Entity.Wishlist.WishListItem;
import com.ecommerce.tradeon.Entity.product.Product;
import com.ecommerce.tradeon.Repository.WishListItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WishListItemService {

    private final WishListItemRepository wishListItemRepository;
    private final WishListService wishListService;
    private final ProductService productService;

    @Transactional
    public WishListItem saveWishItem(WishListItemDto wishListItemDto) {
        WishListItem wishListItem = new WishListItem();
        Product productEntity = productService.getProductEntity(wishListItemDto.getProductDto().getId());
        WishList wishList = wishListService.findWishListEntity(wishListItemDto.getWishListId());

        wishListItem.addProduct(productEntity);
        wishListItem.addWishList(wishList);

        return wishListItemRepository.save(wishListItem);
    }

    public List<WishListItemDto> findByWishlistId(Long wishListId) {
        return wishListItemRepository.findByWishList_Id(wishListId)
                .stream()
                .map(WishListItemDto::setForm)
                .toList();
    }
}
