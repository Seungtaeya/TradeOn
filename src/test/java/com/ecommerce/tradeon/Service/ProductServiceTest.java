package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Dto.Product.ProductDto;
import com.ecommerce.tradeon.Dto.Product.ProductOptionDto;
import com.ecommerce.tradeon.Entity.Category.Category;
import com.ecommerce.tradeon.Entity.Member.Member;
import com.ecommerce.tradeon.Entity.product.Product;
import com.ecommerce.tradeon.Entity.product.ProductOption;
import com.ecommerce.tradeon.Entity.vo.Address;
import com.ecommerce.tradeon.Repository.CategoryRepository;
import com.ecommerce.tradeon.Repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

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
    @Transactional
    public void modify() throws Exception {
        //given
        ProductDto productDto = new ProductDto(1L,1L,null,"testproduct","test",10000,100,true);
        List<ProductOptionDto> listop = new ArrayList<>();
        ProductOptionDto op = new ProductOptionDto();
        ProductOptionDto op1 = new ProductOptionDto();
        op.setName("색상");
        op.setOptionValue("브라운");
        listop.add(op);

        op1.setName("색상");
        op1.setOptionValue("블랙");
        listop.add(op1);

        productDto.setOptions(listop);
        Product product = productService.createProduct(productDto, null);

        for (ProductOption option : product.getOptions()) {
            System.out.println("option.getOptionValue() = " + option.getOptionValue());
        }

        //when
        ProductDto productOne = productService.getProductOne(1L);

        List<String> color = productService.findProductOptionByOptionName(productOne.getId(), "색상");
        for (String s : color) {
            System.out.println("s = " + s);
        }
        //then
        assertThat(productDto.getTitle()).isEqualTo(productOne.getTitle());
        assertThat(productDto.getDescription()).isEqualTo(productOne.getDescription());
        assertThat(op.getOptionValue()).isEqualTo(color.get(0));
        assertThat(op1.getOptionValue()).isEqualTo(color.get(1));
    }
}