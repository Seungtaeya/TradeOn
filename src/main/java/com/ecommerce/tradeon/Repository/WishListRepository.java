package com.ecommerce.tradeon.Repository;

import com.ecommerce.tradeon.Entity.Member.Member;
import com.ecommerce.tradeon.Entity.Wishlist.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.jar.JarFile;

public interface WishListRepository extends JpaRepository<WishList, Long> {

    Long member(Member member);

    List<WishList> findByMember_Id(Long memberId);
}
