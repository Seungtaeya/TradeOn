package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Dto.Product.ProductDto;
import com.ecommerce.tradeon.Entity.Category.Category;
import com.ecommerce.tradeon.Entity.Member.Member;
import com.ecommerce.tradeon.Entity.product.Product;
import com.ecommerce.tradeon.Entity.vo.Address;
import com.ecommerce.tradeon.Repository.CategoryRepository;
import com.ecommerce.tradeon.Repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    MemberRepository memberRepository ;

    @Autowired
    CategoryRepository categoryRepository;


    @BeforeEach
    void beforeEach() {
        Member member = new Member("aaa@aaa.com",new Address("1","2","3"),"qwe","aaa","010-222-333");
        memberRepository.save(member);

        Category category = new Category("test",null);
        categoryRepository.save(category);
    }
    @Test
    public void create () throws Exception {
        //given
        ProductDto productDto = new ProductDto(1L,1L,null,"testproduct","test",10000,100,true);
        Product product = productService.createProduct(productDto, null);
        //when

        //then
        assertThat(product.getTitle()).isEqualTo(productDto.getTitle());
        assertThat(product.getCategory().getId()).isEqualTo(productDto.getCategory_id());
        assertThat(product.getPrice()).isEqualTo(productDto.getPrice());
    }

    @Test
    public void modify() throws Exception {
        //given
        ProductDto productDto = new ProductDto(1L,1L,null,"testproduct","test",10000,100,true);
        Product product = productService.createProduct(productDto, null);

        ProductDto productDto1 = new ProductDto(1L, 1L,null,"modify","modify",10,100,false);
        //when
//        productService.modifyProduct(product.getId(),productDto1);
        ProductDto productOne = productService.getProductOne(1L);
        //then

        assertThat(productDto1.getTitle()).isEqualTo(productOne.getTitle());
        assertThat(productDto.getTitle()).isNotEqualTo(productOne.getTitle());
        assertThat(productDto1.getDescription()).isEqualTo(productOne.getDescription());
        assertThat(productDto.getDescription()).isNotEqualTo(productOne.getDescription());
    }
}