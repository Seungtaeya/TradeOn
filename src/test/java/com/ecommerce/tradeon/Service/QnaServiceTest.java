package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Dto.Category.CategoryDto;
import com.ecommerce.tradeon.Dto.Product.ProductDto;
import com.ecommerce.tradeon.Dto.Qna.QnaDto;
import com.ecommerce.tradeon.Entity.Member.Member;
import com.ecommerce.tradeon.Entity.Qna.Qna;
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
class QnaServiceTest {

    @Autowired QnaService qnaService;
    @Autowired ProductService productService;
    @Autowired MemberService memberService;
    @Autowired CategoryService categoryService;

    @BeforeEach
    public void forEach() {
        Member member = new Member("aaa@aaa.com",new Address("1","2","3"),"qwe","aaa","010-222-333");
        memberService.join(member);

        Member admin = new Member("aaa11123@aaa.com",new Address("1","2","3"),"qwe","Admin","010-2222-333");
        memberService.join(admin);

        CategoryDto category = new CategoryDto("test",null);
        categoryService.createCategory(category);

        ProductDto productDto = new ProductDto(1L,1L,null,"testproduct","test",10000,100,true);
        Product product = productService.createProduct(productDto, null);

        ProductDto productDto1 = new ProductDto(1L,1L,null,"test","test",10,100,true);
        Product product1 = productService.createProduct(productDto1, null);
    }

    @Test
    @Transactional
    public void createQna() throws Exception {
        //given
        QnaDto qnaDto = new QnaDto();
        qnaDto.setContent("질문 qna");
        qnaDto.setProductId(1L);
        qnaDto.setMemberId(1L);
        //when
        Qna qna = qnaService.saveQna(qnaDto);
        List<QnaDto> allQna = qnaService.findAllQna();
        //then
        assertThat(allQna.get(0).getContent()).isEqualTo(qna.getContent()).isEqualTo(qnaDto.getContent());
        assertThat(allQna.get(0).getMemberId()).isEqualTo(qna.getMember().getId()).isEqualTo(qnaDto.getMemberId());

        QnaDto reply = new QnaDto();
        reply.setMemberId(2L);
        reply.setProductId(1L);
        reply.setContent("답변 qna");
        reply.setParentId(1L);

        Qna qna1 = qnaService.saveQna(reply);

        List<QnaDto> qnaReply = qnaService.findAllQna();

        for (QnaDto dto : qnaReply) {
            System.out.println("dto.getContent() = " + dto.getContent());
            System.out.println("dto.getParentContent() = " + dto.getParentContent());
            System.out.println("======================");
        }

        assertThat(qnaReply.get(0).getProductId()).isEqualTo(qna1.getProduct().getId());
        assertThat(qnaReply.get(0).getContent()).isEqualTo(qna1.getParent().getContent());
        assertThat(qna.getContent()).isEqualTo("질문 qna");
        assertThat(qnaReply.get(1).getContent()).isEqualTo("답변 qna");
    }

}