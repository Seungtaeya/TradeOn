package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Dto.Member.myPageMemberDto;
import com.ecommerce.tradeon.Entity.Member.Member;
import com.ecommerce.tradeon.Exceptions.CustomeMemberException;
import com.ecommerce.tradeon.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void join(Member member) {
        memberRepository.save(member);
    }

    public Boolean existsEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    public myPageMemberDto findMyPageMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new CustomeMemberException("존재하지 않는 회원 입니다."));

        return new myPageMemberDto(member.getUsername(),member.getEmail(), member.getPhone(), member.getCreated_at());
    }
}
