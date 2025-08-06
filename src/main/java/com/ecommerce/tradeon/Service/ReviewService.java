package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Dto.Review.ReviewDto;
import com.ecommerce.tradeon.Entity.Member.Member;
import com.ecommerce.tradeon.Entity.Review.Review;
import com.ecommerce.tradeon.Entity.product.Product;
import com.ecommerce.tradeon.Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductService productService;
    private final MemberService memberService;

    @Transactional
    public ReviewDto saveReview(ReviewDto reviewDto) {
        Review review = new Review(reviewDto.getContent(), reviewDto.getRating());
        Member byMemberId = memberService.findByMemberId(reviewDto.getMemberId());
        Product productEntity = productService.getProductEntity(reviewDto.getProductId());

        review.assignMember(byMemberId);
        review.assignProduct(productEntity);

        Review save = reviewRepository.save(review);
        return ReviewDto.setForm(save);
    }

    public List<ReviewDto> findReviewsByMember(Long memberId) {
        return reviewRepository.findByMemberId(memberId)
                .stream()
                .map(ReviewDto::setForm)
                .toList();
    }

    public List<ReviewDto> findAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(ReviewDto::setForm)
                .toList();
    }

    public List<ReviewDto> findReviewByProductId(Long productId) {
        return reviewRepository.findByProductId(productId)
                .stream()
                .map(ReviewDto::setForm)
                .toList();
    }

    public ReviewDto findByReviewId(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("선택하신 리뷰가 없습니다."));
        return ReviewDto.setForm(review);
    }

    @Transactional
    public void updateReview(Long reviewId, ReviewDto dto) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("선택하신 리뷰가 없습니다."));

        review.changeContent(dto.getContent());
        review.changeRating(dto.getRating());
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("선택하신 리뷰가 없습니다."));
        reviewRepository.delete(review);
    }

}
