package com.ecommerce.tradeon.Repository;

import com.ecommerce.tradeon.Dto.Product.ProductDto;
import com.ecommerce.tradeon.Entity.Category.Category;
import com.ecommerce.tradeon.Entity.Member.Member;
import com.ecommerce.tradeon.Entity.vo.Address;
import com.ecommerce.tradeon.Service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductCategoryQueryRepositoryImplTest {


    @Autowired
    MemberRepository memberRepository ;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductService productService;

    @BeforeEach
    void beforeEach() {
        Member member = new Member("aaa@aaa.com",new Address("1","2","3"),"qwe","aaa","010-222-333");
        memberRepository.save(member);

        Category category = new Category("test",null);
        categoryRepository.save(category);

        Category category1 = new Category("query",null);
        categoryRepository.save(category1);
    }

    @Test
    public void 카테고리_조회_테스트() throws Exception {
        //given
        ProductDto productDto = new ProductDto(1L,1L,null,"test","ㅅㄷㅅㄷㄱ",10,100,true);
        productService.createProduct(productDto, null,null);
        ProductDto productDto1 = new ProductDto(1L,1L,null,"wsfsf","ㅅㄷㅅㄷㄱ",10,100,true);
        productService.createProduct(productDto1, null,null);
        ProductDto productDto2 = new ProductDto(1L,1L,null,"ghd","ㅅㄷㅅㄷㄱ",10,100,true);
        productService.createProduct(productDto2, null,null);
        ProductDto productDto3 = new ProductDto(1L,2L,null,"142","ㅅㄷㅅㄷㄱ",10,100,true);
        productService.createProduct(productDto3, null,null);


        //when
        Page<ProductDto> find = productService.findProductByCategoryId(1L, PageRequest.of(0, 10));

        for (ProductDto dto : find) {
            System.out.println("dto.getTitle() = " + dto.getTitle());
        }
        //then
        assertThat(find.getTotalElements()).isEqualTo(3);
    }
}