package com.ecommerce.tradeon.Controller.Admin;

import com.ecommerce.tradeon.Dto.Banner.BannerDto;
import com.ecommerce.tradeon.Service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class BannerController {

    private final BannerService bannerService;

    @GetMapping("/banners")
    public String BannerForm(Model model) {

        List<BannerDto> banners = bannerService.getBanners();

        model.addAttribute("banners", banners);
        return "Admin/Banner/bannerForm";
    }

    @PostMapping("/banners")
    public String addBanner(BannerDto bannerDto) {
        bannerService.saveBanner(bannerDto);
        return "redirect:/";
    }

    @PostMapping("/banner/{id}/delete")
    public String removeBanner(@PathVariable(name = "id")Long bannerId) {
        bannerService.deleteBanner(bannerId);

        return "redirect:/admin/banners";
    }
}
