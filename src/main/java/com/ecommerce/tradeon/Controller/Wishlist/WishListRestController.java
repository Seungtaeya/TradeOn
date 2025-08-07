package com.ecommerce.tradeon.Controller.Wishlist;

import com.ecommerce.tradeon.Dto.Product.ProductDto;
import com.ecommerce.tradeon.Dto.Session.SessionMember;
import com.ecommerce.tradeon.Dto.WishList.WishListDto;
import com.ecommerce.tradeon.Dto.WishList.WishListItemDto;
import com.ecommerce.tradeon.Service.ProductService;
import com.ecommerce.tradeon.Service.WishListItemService;
import com.ecommerce.tradeon.Service.WishListService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WishListRestController {

    private final WishListService wishListService;
    private final WishListItemService wishListItemService;
    private final ProductService productService;

    @PostMapping("/wishlist")
    public ResponseEntity<Void> createWishlist(@RequestBody WishListDto dto, HttpSession session) {
        SessionMember loginMember = getSessionMember(session);
        dto.setMemberId(loginMember.getId());
        wishListService.saveWishList(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/wishlist/{id}/items")
    public List<WishListItemDto> getWishListItemsByWishlistId(@PathVariable(name = "id") Long wishlistId) {
        return wishListItemService.findByWishlistId(wishlistId);
    }

    @GetMapping("/api/wishlist")
    public List<WishListDto> getWishlistsByMemberId(HttpSession session) {
        SessionMember loginMember = getSessionMember(session);
        return wishListService.findWishListByMemberId(loginMember.getId());
    }

    @PostMapping("/wishlist/{WishListId}/add")
    public ResponseEntity<Void> addProductToWishlist(@PathVariable(name = "WishListId") Long wishListId,
                                                     @RequestBody Long productId,
                                                     HttpSession session) {
        System.out.println("wishListId = " + wishListId);
        SessionMember loginMember = getSessionMember(session);
        ProductDto productOne = productService.getProductOne(productId);
        WishListItemDto dto = new WishListItemDto();
        dto.setWishListId(wishListId);
        dto.setProductDto(productOne);
        wishListItemService.saveWishItem(dto);
        return ResponseEntity.ok().build();
    }
    private static SessionMember getSessionMember(HttpSession session) {
        return (SessionMember) session.getAttribute("loginMember");
    }
}
