package com.ecommerce.tradeon.Controller.Cart;

import com.ecommerce.tradeon.Dto.Cart.CartDto;
import com.ecommerce.tradeon.Dto.Session.SessionMember;
import com.ecommerce.tradeon.Exceptions.CustomLoginException;
import com.ecommerce.tradeon.Service.CartItemService;
import com.ecommerce.tradeon.Service.CartService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartItemService cartItemService;

    @GetMapping("/cart")
    public String cart(HttpSession session, Model model) {
        SessionMember loginMember = (SessionMember) session.getAttribute("loginMember");

        loginCheck(loginMember);

        CartDto all = cartService.findByMemberId(loginMember.getId());
        int totalPrice = all.getItems()
                .stream()
                .mapToInt(i -> i.getProduct().getPrice())
                .sum();


        model.addAttribute("cart",all);
        model.addAttribute("totalPrice", totalPrice);

        return "Cart/Cart";
    }

    @PostMapping("/cart/add/{id}")
    public String createCart(@PathVariable(name = "id")Long productId, HttpSession session) {
        SessionMember loginMember = (SessionMember) session.getAttribute("loginMember");

        loginCheck(loginMember);

        CartDto cart = cartService.createCart(productId, loginMember.getId());


        return "redirect:/cart";
    }

    @PostMapping("/cart/remove")
    public String remove(Long itemProductId, Long cartId, HttpSession session) {
        SessionMember loginMember = (SessionMember) session.getAttribute("loginMember");

        loginCheck(loginMember);

        System.out.println("itemProductId = " + itemProductId);
        System.out.println("cartId = " + cartId);

        cartItemService.CartItemRemove(cartId,itemProductId);
        return "redirect:/cart";
    }

    private void loginCheck(SessionMember loginMember) {

        if(loginMember == null) {
            throw new CustomLoginException();
        }
    }
}
