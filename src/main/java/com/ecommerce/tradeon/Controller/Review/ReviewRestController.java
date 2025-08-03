package com.ecommerce.tradeon.Controller.Review;

import com.ecommerce.tradeon.Dto.Review.ReviewDto;
import com.ecommerce.tradeon.Entity.Review.Review;
import com.ecommerce.tradeon.Service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewRestController {

    public final ReviewService reviewService;

    @PostMapping("/product/review")
    public ReviewDto createReview(@RequestBody ReviewDto dto) {
        ReviewDto reviewDto = reviewService.saveReview(dto);

        return reviewDto;
    }
}
