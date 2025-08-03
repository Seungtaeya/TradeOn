package com.ecommerce.tradeon.Repository;

import com.ecommerce.tradeon.Entity.Member.Member;
import com.ecommerce.tradeon.Entity.Review.Review;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMemberId(Long memberId);

}
