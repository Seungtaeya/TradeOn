package com.ecommerce.tradeon.Controller.Admin.Notice;

import com.ecommerce.tradeon.Dto.Notice.NoticeDto;
import com.ecommerce.tradeon.Service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/admin/notices")
    public String notice() {
        return "Notice/NoticeList";
    }

    @PostMapping("/admin/notice")
    public String saveNotice(NoticeDto noticeDto) {
        noticeService.saveNotice(noticeDto);

        return "redirect:/admin/notices";
    }

    @GetMapping("/admin/notice")
    public String createNoticeForm() {
        return "Notice/NoticeForm";
    }

    @GetMapping("/admin/noticeDetail/{id}")
    public String NoticeDetail(@PathVariable(name = "id")Long noticeId, Model model) {
        NoticeDto notice = noticeService.findNoticeOneById(noticeId);

        model.addAttribute("notice",notice);

        return "Notice/NoticeDetail";
    }
}
