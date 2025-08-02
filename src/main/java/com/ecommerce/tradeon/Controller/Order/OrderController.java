package com.ecommerce.tradeon.Controller.Order;

import com.ecommerce.tradeon.Dto.Order.OrderDetailDto;
import com.ecommerce.tradeon.Dto.Order.OrderRequest;
import com.ecommerce.tradeon.Dto.Session.SessionMember;
import com.ecommerce.tradeon.Service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    public String Order(OrderRequest request, HttpSession session) {
        SessionMember loginMember = (SessionMember) session.getAttribute("loginMember");

        if(loginMember == null) {
            return "redirect:/login";
        }

        orderService.createOrder(request, loginMember.getId());

        return "redirect:/mypage/orders";
    }

    @GetMapping("/mypage/orders/{id}")
    public String orderDetail(@PathVariable(name = "id") Long orderId, Model model) {
        OrderDetailDto orderDetail = orderService.findOrderDetail(orderId);

        model.addAttribute("order", orderDetail);
        return "Member/orderDetail";
    }
}
