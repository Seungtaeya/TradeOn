package com.ecommerce.tradeon.Controller.Admin.Notice;

import com.ecommerce.tradeon.Dto.Notice.NoticeDto;
import com.ecommerce.tradeon.Service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class NoticeRestController {

    private final NoticeService noticeService;

    @GetMapping("/api/Notices")
    public List<NoticeDto> getNotices() {
        return noticeService.getNotices();
    }
}
