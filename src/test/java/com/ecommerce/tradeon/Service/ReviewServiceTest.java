package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Dto.Category.CategoryDto;
import com.ecommerce.tradeon.Dto.Product.ProductDto;
import com.ecommerce.tradeon.Dto.Review.ReviewDto;
import com.ecommerce.tradeon.Entity.Member.Member;
import com.ecommerce.tradeon.Entity.Review.Review;
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

@SpringBootTest
class ReviewServiceTest {

    @Autowired
    ReviewService reviewService;

    @Autowired
    private ProductService productService;

    @Autowired
    MemberService memberService;

    @Autowired
    CategoryService categoryService;

    @BeforeEach
    public void forEach() {
        Member member = new Member("aaa@aaa.com",new Address("1","2","3"),"qwe","aaa","010-222-333");
        memberService.join(member);

        CategoryDto category = new CategoryDto("test",null);
        categoryService.createCategory(category);

        ProductDto productDto = new ProductDto(1L,1L,null,"testproduct","test",10000,100,true);
        Product product = productService.createProduct(productDto, null,null);

        ProductDto productDto1 = new ProductDto(1L,1L,null,"test","test",10,100,true);
        Product product1 = productService.createProduct(productDto1, null, null);
    }

    @Test
    @Transactional
    public void saveTest () throws Exception {
        //given
        ReviewDto reviewDto = new ReviewDto(1L,1L, "testCode",5L);
        ReviewDto reviewDto1 = new ReviewDto(1L,2L, "2",3L);
        Product productEntity = productService.getProductEntity(1L);
        Member byMemberId = memberService.findByMemberId(1L);

        //when
        reviewService.saveReview(reviewDto);
        reviewService.saveReview(reviewDto1);
        List<ReviewDto> allReviews = reviewService.findAllReviews();
        List<ReviewDto> reviewsByMember = reviewService.findReviewsByMember(1L);
        //then
        assertThat(allReviews.size()).isEqualTo(2);
        assertThat(reviewsByMember.get(0).getContent()).isEqualTo("testCode");
        assertThat(reviewsByMember.get(1).getContent()).isEqualTo("2");
    }
}