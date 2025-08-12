package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Dto.Cart.CartDto;
import com.ecommerce.tradeon.Dto.Order.*;
import com.ecommerce.tradeon.Dto.Product.ProductDto;
import com.ecommerce.tradeon.Entity.Cart.Cart;
import com.ecommerce.tradeon.Entity.Cart.CartItem;
import com.ecommerce.tradeon.Entity.Member.Member;
import com.ecommerce.tradeon.Entity.Order.Order;
import com.ecommerce.tradeon.Entity.Order.OrderItem;
import com.ecommerce.tradeon.Entity.product.Product;
import com.ecommerce.tradeon.Enums.OrderStatus;
import com.ecommerce.tradeon.Repository.OrderRepository;
import com.querydsl.core.QueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CartService cartService;
    private final MemberService memberService;
    private final OrderItemService orderItemService;

    @Transactional
    public Order createOrder(OrderRequest request, OrderRequestDto dto, Long memberId) {
        Member byMemberId = memberService.findByMemberId(memberId);
        Order order = new Order();
        OrderItem orderItem = new OrderItem();
        List<OrderItemOptionDto> list = new ArrayList<>();

        if(dto.getSelectedOptions() != null) {
            for (Map.Entry<String, String> entry : dto.getSelectedOptions().entrySet()) {
                OrderItemOptionDto optionDto = new OrderItemOptionDto();
                optionDto.setName(entry.getKey());
                optionDto.setOptionValue(entry.getValue());
                list.add(optionDto);
            }
        }

        order.assignsMember(byMemberId);

        if(request.isOrdercart()) {
            CartDto byMemberId1 = cartService.findByMemberId(memberId);

            List<CartItem> items = byMemberId1.getItems();

            for (CartItem item : items) {
                orderItemService.createOrderItem(order,list, item.getProduct());
            }

            order.addOrderStatus(OrderStatus.ORDERED);
            Order save = orderRepository.save(order);
            cartService.clear();
            return save;
        }

        Product productEntity = productService.getProductEntity(request.getProductId());

        orderItemService.createOrderItem(order,list, productEntity);
        order.addOrderStatus(OrderStatus.ORDERED);
        return orderRepository.save(order);
    }

    public List<OrderDto> findAll(Long memberId) {
        return orderRepository.findByMemberId(memberId)
                .stream()
                .map(OrderDto::setForm)
                .toList();
    }

    public OrderDetailDto findOrderDetail(Long orderId) {
        Order byOrderId = orderRepository.findByOrderId(orderId);

        return  OrderDetailDto.setForm(byOrderId);
    }

    public List<SalesOrderDto> findSalesOrderAll(Long memberId) {
        return orderRepository.findSalesOrderAll(memberId);
    }

    @Transactional
    public void updateStatus(Long orderId, String status) {
        Order byOrderId = orderRepository.findByOrderId(orderId);
        byOrderId.changeStatus(status);
    }
}
