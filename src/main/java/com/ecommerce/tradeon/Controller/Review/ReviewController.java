package com.ecommerce.tradeon.Controller.Review;


import com.ecommerce.tradeon.Dto.Order.OrderDetailDto;
import com.ecommerce.tradeon.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final OrderService orderService;

    @GetMapping("/mypage/orders/review/{id}")
    public String showReviewForm(@PathVariable(name = "id") Long orderId, Model model) {
        OrderDetailDto orderDetail = orderService.findOrderDetail(orderId);
        model.addAttribute("order",orderDetail);
        return "Review/ReviewForm";
    }
}
