package com.ecommerce.tradeon.Controller.Admin;

import com.ecommerce.tradeon.Service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BannerController {

    private final BannerService bannerService;

    @GetMapping("/admin/banner")
    public String BannerForm() {
        return "Admin/Banner/bannerForm";
    }
}
