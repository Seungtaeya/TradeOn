package com.ecommerce.tradeon.Controller.auth;

import com.ecommerce.tradeon.Dto.Session.SessionMember;
import com.ecommerce.tradeon.Dto.auth.LoginDto;
import com.ecommerce.tradeon.Dto.auth.RegisterMemberDto;
import com.ecommerce.tradeon.Entity.Member.Member;
import com.ecommerce.tradeon.Service.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/register")
    public String register() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerMember(RegisterMemberDto memberDto) {
        authService.join(memberDto);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String loginMember(LoginDto loginDto,String uri, HttpSession session) {
        Member login = authService.login(loginDto);

        session.setAttribute("loginMember", new SessionMember(login));

        if(uri != null) {
            return "redirect:" + uri;
        }

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logoutMember(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
