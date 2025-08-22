package com.ecommerce.tradeon.Repository;

import com.ecommerce.tradeon.Entity.Cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("select c from Cart c where c.member.id = :memberId")
    Cart findMemberId(Long memberId);

}
