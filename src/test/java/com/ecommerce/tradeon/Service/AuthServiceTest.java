package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Dto.auth.AddressDto;
import com.ecommerce.tradeon.Dto.auth.LoginDto;
import com.ecommerce.tradeon.Dto.auth.RegisterMemberDto;
import com.ecommerce.tradeon.Entity.Member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class AuthServiceTest {

    @Autowired
    AuthService authService;


    @Test
    public void login() throws Exception {
        //given
        AddressDto address = new AddressDto("서울", "강남구", "123");
        RegisterMemberDto member = new RegisterMemberDto("name","abc@aaa.com","qwe","010-111-22",address);
        authService.join(member);
        LoginDto loginDto = new LoginDto("abc@aaa.com","qwe");
        //when
        Member login = authService.login(loginDto);

        //then
        assertThat(login.getEmail()).isEqualTo(loginDto.getEmail());
        assertThat(login.getPassword()).isEqualTo(loginDto.getPassword());
    }
}