package com.ecommerce.tradeon.Controller.Admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {

    @GetMapping("/admin")
    public String AdminHome() {
        return "Admin/Dashboard";
    }
}
