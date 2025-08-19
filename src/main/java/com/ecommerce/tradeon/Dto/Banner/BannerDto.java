package com.ecommerce.tradeon.Dto.Banner;

import com.ecommerce.tradeon.Entity.Banner.Banner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BannerDto {

    private Long id;
    private MultipartFile image;

    private String imageUrl;
    private String linkUrl;
    private String imageName;

    public static BannerDto setForm(Banner banner) {
        BannerDto dto = new BannerDto();

        dto.setId(banner.getId());
        dto.setImageUrl(banner.getImageUrl());
        dto.setLinkUrl(banner.getLinkUrl());
        dto.setImageName(banner.getImageName());

        return dto;
    }
}
