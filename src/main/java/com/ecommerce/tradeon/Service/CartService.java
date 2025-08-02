package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Dto.Cart.CartDto;
import com.ecommerce.tradeon.Entity.Cart.Cart;
import com.ecommerce.tradeon.Entity.Member.Member;
import com.ecommerce.tradeon.Entity.product.Product;
import com.ecommerce.tradeon.Repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final MemberService memberService;
    private final ProductService productService;
    private final CartItemService cartItemService;

    @Transactional
    public CartDto createCart(Long productId, Long memberId) {
        Member byMemberId = memberService.findByMemberId(memberId);
        Product productEntity = productService.getProductEntity(productId);
        Cart existingCart = cartRepository.findMemberId(memberId);

        if(existingCart != null) {
            cartItemService.createCartItem(existingCart, productEntity);
            return CartDto.setForm(existingCart);
        }

        Cart newcart = new Cart();
        newcart.assignMember(byMemberId);
        cartRepository.save(newcart);

        cartItemService.createCartItem(newcart,productEntity);

        return CartDto.setForm(newcart);
    }

    @Transactional
    public void clear() {
        cartRepository.deleteAll();
    }

    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카트 아이디 입니다."));
    }

    public CartDto findByMemberId(Long memberId) {

        if(cartRepository.findMemberId(memberId) == null) {
            Cart cart = new Cart();
            Member byMemberId = memberService.findByMemberId(memberId);
            cart.assignMember(byMemberId);

            cartRepository.flush();
            cartRepository.save(cart);

            return CartDto.setForm(cart);
        }
        Cart memberId1 = cartRepository.findMemberId(memberId);
        return CartDto.setForm(memberId1);
    }

    public List<Cart>  findAllEntity()  {
        return cartRepository.findAll();
    }


}
