package com.ecommerce.tradeon.Controller.Admin.Notice;

import com.ecommerce.tradeon.Dto.Notice.NoticeDto;
import com.ecommerce.tradeon.Service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/api/notice")
    public ResponseEntity<Void> saveNotice(NoticeDto noticeDto) {
        noticeService.saveNotice(noticeDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/notices/delete/{id}")
    public ResponseEntity<Void> deleteNotice(@PathVariable(name = "id") Long noticeId) {
        noticeService.DeleteNotice(noticeId);
        return ResponseEntity.noContent().build();
    }
}
