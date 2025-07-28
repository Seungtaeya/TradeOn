package com.ecommerce.tradeon.Controller.myPage;

import com.ecommerce.tradeon.Dto.Member.myPageMemberDto;
import com.ecommerce.tradeon.Dto.Session.SessionMember;
import com.ecommerce.tradeon.Entity.Member.Member;
import com.ecommerce.tradeon.Service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.http.HttpRequest;

@Controller
@RequiredArgsConstructor
public class MyPageController {

    private final MemberService memberService;

    @GetMapping("/mypage")
    public String myPage(HttpSession session, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
        SessionMember loginMember = (SessionMember) session.getAttribute("loginMember");

        if (loginMember == null) {
            redirectAttributes.addFlashAttribute("uri",request.getRequestURI());
            return "redirect:/login";
        }

        myPageMemberDto myPageMember = memberService.findMyPageMember(loginMember.getId());

        model.addAttribute("member",myPageMember);

        return "Member/mypage";
    }
}
