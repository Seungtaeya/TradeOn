package com.ecommerce.tradeon.Controller.Admin.Notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoticeController {

    @GetMapping("/admin/notices")
    public String notice() {
        return "Notice/NoticeList";
    }
}
