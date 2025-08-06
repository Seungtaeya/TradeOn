package com.ecommerce.tradeon.Entity.Review;

import com.ecommerce.tradeon.Entity.Member.Member;
import com.ecommerce.tradeon.Entity.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Review {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String content;
    private Long rating;
    private LocalDateTime createdAt;



    public Review(String content, Long rating) {
        this.content = content;
        this.rating = rating;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void changeRating(Long rating) {
        this.rating = rating;
    }

    public void assignProduct(Product product) {
        this.product = product;
    }

    public void assignMember(Member member) {
        this.member = member;
    }

    @PrePersist
    public void createdAt() {
        this.createdAt = LocalDateTime.now();
    }
}
