package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Dto.Cart.CartDto;
import com.ecommerce.tradeon.Dto.Order.OrderDetailDto;
import com.ecommerce.tradeon.Dto.Order.OrderDto;
import com.ecommerce.tradeon.Dto.Order.OrderRequest;
import com.ecommerce.tradeon.Dto.Order.SalesOrderDto;
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

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CartService cartService;
    private final MemberService memberService;
    private final OrderItemService orderItemService;
    private final EntityManager em;


    @Transactional
    public Order createOrder(OrderRequest request, Long memberId) {
        Member byMemberId = memberService.findByMemberId(memberId);
        Order order = new Order();
        OrderItem orderItem = new OrderItem();

        order.addOrderStatus(OrderStatus.PENDING);
        order.assignsMember(byMemberId);

        if(request.isOrdercart()) {
            CartDto byMemberId1 = cartService.findByMemberId(memberId);

            List<CartItem> items = byMemberId1.getItems();

            for (CartItem item : items) {
                orderItemService.createOrderItem(order, item.getProduct());
            }
            order.addOrderStatus(OrderStatus.PAID);
            Order save = orderRepository.save(order);
            cartService.clear();
            return save;
        }

        Product productEntity = productService.getProductEntity(request.getProductId());

        orderItemService.createOrderItem(order, productEntity);
        order.addOrderStatus(OrderStatus.PAID);
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
        // 1. 회원 아이디로 조회
        // 2. 회원이 판매중인 제품이 있는지 조회
        // 3. 판매중인 제품이 있으면 그 제품이 주문이 들어왔는지 조회
        List<OrderItem> orderItems = orderItemService.findOrderItems(memberId);
        // 4. 주문이 들어 왔다면 그것을 가져와서 컨트롤러에 전달
    }
}
