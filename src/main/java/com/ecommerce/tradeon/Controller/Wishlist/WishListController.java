package com.ecommerce.tradeon.Controller.Wishlist;

import com.ecommerce.tradeon.Dto.WishList.WishListItemDto;
import com.ecommerce.tradeon.Service.WishListItemService;
import jakarta.servlet.http.HttpSession;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class WishListController {

    private final WishListItemService wishListItemService;

    @GetMapping("/mypage/wishlist")
    public String wishlist() {
        return "WishList/WishList";
    }

    @GetMapping("/wishlist/select/{productId}")
    public String showWishlistSelected(@PathVariable(name = "productId")Long productId) {

        return "WishList/WishListSelect";
    }

}
