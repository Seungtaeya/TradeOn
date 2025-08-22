package com.ecommerce.tradeon.Controller.myPage;

import com.ecommerce.tradeon.Dto.Member.MemberDto;
import com.ecommerce.tradeon.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MypageRestController {

    private final MemberService memberService;

    @PutMapping("/user/update/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable(name = "id")Long memberId, @RequestBody MemberDto memberDto) {

        System.out.println("memberDto.getUsername() = " + memberDto.getUsername());
        System.out.println("memberDto.getCity() = " + memberDto.getCity());
        memberService.updateUser(memberId,memberDto);
        return ResponseEntity.noContent().build();
    }

}
