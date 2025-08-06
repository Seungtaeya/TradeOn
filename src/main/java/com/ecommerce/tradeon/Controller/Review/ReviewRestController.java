package com.ecommerce.tradeon.Controller.Review;

import com.ecommerce.tradeon.Dto.Qna.QnaDto;
import com.ecommerce.tradeon.Dto.Review.ReviewDto;
import com.ecommerce.tradeon.Dto.Session.SessionMember;
import com.ecommerce.tradeon.Entity.Review.Review;
import com.ecommerce.tradeon.Service.ReviewService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewRestController {

    public final ReviewService reviewService;

    @PostMapping("/product/review")
    public ReviewDto createReview(@RequestBody ReviewDto dto) {
        ReviewDto reviewDto = reviewService.saveReview(dto);

        return reviewDto;
    }

    @GetMapping("/product/{id}")
    public List<ReviewDto> getReviewsByProductId(@PathVariable(name = "id")Long productId) {
        List<ReviewDto> reviewByProductId = reviewService.findReviewByProductId(productId);
        return reviewByProductId;
    }

    @GetMapping("/mypage/review")
    public List<ReviewDto> MyReviewsForm(HttpSession session) {
        SessionMember loginMember = (SessionMember) session.getAttribute("loginMember");

        return reviewService.findReviewsByMember(loginMember.getId());
    }

    @GetMapping("/review/{id}")
    public ReviewDto ReviewEditForm(@PathVariable(name = "id") Long reviewId) {
        return reviewService.findByReviewId(reviewId);
    }

    @PutMapping("/review/edit/{id}")
    public ResponseEntity<Void> updateReview(@PathVariable(name = "id") Long reviewId, @RequestBody ReviewDto reviewDto) {
        reviewService.updateReview(reviewId, reviewDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/review/delete/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable(name = "id") Long reviewId) {
        reviewService.deleteReview(reviewId);

        return ResponseEntity.ok().build();
    }
}
