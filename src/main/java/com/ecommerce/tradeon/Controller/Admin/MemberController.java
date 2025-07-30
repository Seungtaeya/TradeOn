package com.ecommerce.tradeon.Controller.Admin;

import com.ecommerce.tradeon.Dto.Member.AdminMemberDto;
import com.ecommerce.tradeon.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/admin/members")
    public String members(Model model) {
        List<AdminMemberDto> members = memberService.AdminFindMembers();

        model.addAttribute("members", members);

        return "Member/AdminMembers";
    }

    @GetMapping("/admin/members/{id}")
    public String removeMember(@PathVariable(name = "id") Long memberId, Model model) {
        AdminMemberDto adminMemberDto = memberService.AdminFindMember(memberId);

        model.addAttribute("member", adminMemberDto);

        return "Member/AdminMemberDetail";
    }

    @PostMapping("/admin/members/{id}/role")
    public String modifyRole(@PathVariable(name = "id") Long id, String role) {
        memberService.AdminChangeRole(id, role);

        return "redirect:/admin/members";
    }

    @PostMapping("/admin/members/{id}/delete")
    public String removeMember(@PathVariable(name = "id") Long memberId) {
        memberService.AdminMemberDelete(memberId);

        return "redirect:/admin/members";
    }
}
