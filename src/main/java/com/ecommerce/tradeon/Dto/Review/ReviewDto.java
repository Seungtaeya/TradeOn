package com.ecommerce.tradeon.Dto.Review;

import com.ecommerce.tradeon.Entity.Review.Review;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReviewDto {

    private Long id;
    private Long memberId;
    private String memberName;
    private Long productId;
    private String productTitle;
    private String content;
    private Long rating;
    private LocalDateTime createdAt;


    public static ReviewDto setForm(Review review) {
        ReviewDto dto = new ReviewDto();
        dto.setId(review.getId());
        dto.setMemberId(review.getMember().getId());
        dto.setMemberName(review.getMember().getUsername());
        dto.setProductId(review.getProduct().getId());
        dto.setProductTitle(review.getProduct().getTitle());
        dto.setContent(review.getContent());
        dto.setCreatedAt(review.getCreatedAt());
        dto.setRating(review.getRating());
        return dto;
    }

    public ReviewDto(Long memberId, Long productId, String content, Long rating) {
        this.memberId = memberId;
        this.productId = productId;
        this.content = content;
        this.rating = rating;
    }
}
