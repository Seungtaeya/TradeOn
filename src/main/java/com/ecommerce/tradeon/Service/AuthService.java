package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Dto.auth.LoginDto;
import com.ecommerce.tradeon.Dto.auth.RegisterMemberDto;
import com.ecommerce.tradeon.Entity.Member.Member;
import com.ecommerce.tradeon.Entity.vo.Address;
import com.ecommerce.tradeon.Exceptions.CustomLoginException;
import com.ecommerce.tradeon.Exceptions.CustomRegisterException;
import com.ecommerce.tradeon.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final MemberRepository memberRepository;

    @Transactional
    public void join(RegisterMemberDto memberDto) {
        Member member = new Member(
                memberDto.getEmail(),
                new Address(memberDto.getAddressdto().getCity(),
                        memberDto.getAddressdto().getStreet(),
                        memberDto.getAddressdto().getZipcode()),
                memberDto.getPassword(),
                memberDto.getUsername(),
                memberDto.getPhone()
        );

        validateDuplicateEmail(member.getEmail());

        memberRepository.save(member);
    }

    private void validateDuplicateEmail(String email) {
        if(memberRepository.existsByEmail(email))
            throw new CustomRegisterException("존재하는 이메일 입니다.");
    }


    public Member login(LoginDto loginDto) {

        Member member = memberRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new CustomLoginException("아이디 또는 비밀번호가 일치하지 않습니다."));

        if(!member.getEmail().equalsIgnoreCase(loginDto.getEmail()) || !member.getPassword().equalsIgnoreCase(loginDto.getPassword())) {
            throw new CustomLoginException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
        return member;
    }
}
