package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Dto.auth.AddressDto;
import com.ecommerce.tradeon.Dto.auth.RegisterMemberDto;
import com.ecommerce.tradeon.Entity.Member.Member;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    public void findByEmail() throws Exception {
        //given
        AddressDto address = new AddressDto();
        RegisterMemberDto memberDto = new RegisterMemberDto("kim","aaaa@naver.com","qwe","010-222-33",address);
        Member member = new Member();
        memberService.join(member);
        //when
        Boolean byEmail = memberService.existsEmail("aaaa@naver.com");
        //then
        System.out.println("byEmail = " + byEmail);

        Assertions.assertThat(byEmail).isTrue();
    }
}