package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Dto.Cart.CartDto;
import com.ecommerce.tradeon.Dto.Category.CategoryDto;
import com.ecommerce.tradeon.Dto.Order.OrderRequest;
import com.ecommerce.tradeon.Dto.Product.ProductDto;
import com.ecommerce.tradeon.Entity.Cart.Cart;
import com.ecommerce.tradeon.Entity.Cart.CartItem;
import com.ecommerce.tradeon.Entity.Category.Category;
import com.ecommerce.tradeon.Entity.Member.Member;
import com.ecommerce.tradeon.Entity.Order.Order;
import com.ecommerce.tradeon.Entity.product.Product;
import com.ecommerce.tradeon.Entity.vo.Address;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    OrderService orderService;
    @Autowired
    MemberService memberService;
    @Autowired
    ProductService productService;
    @Autowired
    CartService cartService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CartItemService cartItemService;

    @BeforeEach
    void Each() {
        Member member = new Member("aaa@aaa.com",new Address("1","2","3"),"qwe","aaa","010-222-333");
        memberService.join(member);

        CategoryDto category = new CategoryDto("test",null);
        categoryService.createCategory(category);

        ProductDto productDto = new ProductDto(1L,1L,null,"testproduct","test",10000,100,true);
        Product product = productService.createProduct(productDto, null);
    }

    @Test
    public void createOrder() throws Exception {
        //given
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrdercart(false);
        Cart cart1 = new Cart();
        Member byMemberId1 = memberService.findByMemberId(1L);

        Product productEntity = productService.getProductEntity(1L);
        cart1.assignMember(byMemberId1);
        CartItem cartItem = cartItemService.createCartItem(cart1, productEntity);

//        Product productEntity1 = productService.getProductEntity(1L);
//        CartItem cartItem1 = cartItemService.createCartItem(cart1, productEntity1);

        //when
        orderRequest.setProductId(productEntity.getId());
        Order order = orderService.createOrder(orderRequest, 1L);
//        List<Order> all = orderService.findAll();

        Member byMemberId = memberService.findByMemberId(1L);
        //then
        assertThat(order.getMember().getId()).isEqualTo(byMemberId.getId());
        assertThat(order.getOrderItems().get(0).getProduct().getTitle()).isEqualTo("testproduct");
//        assertThat(all.get(0).getOrderItems().size()).isEqualTo(1);
    }
}