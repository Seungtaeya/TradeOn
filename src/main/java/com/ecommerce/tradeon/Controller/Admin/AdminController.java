package com.ecommerce.tradeon.Controller.Admin;

import com.ecommerce.tradeon.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final MemberService memberService;

    @GetMapping("/admin")
    public String AdminHome(Model model) {
        Long count = memberService.Count();
        model.addAttribute("userCount", count);

        return "Admin/Dashboard";
    }
}
