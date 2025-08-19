package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Dto.Banner.BannerDto;
import com.ecommerce.tradeon.Entity.Banner.Banner;
import com.ecommerce.tradeon.Repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BannerService {

    private final BannerRepository bannerRepository;

    @Transactional
    public void saveBanner(BannerDto bannerDto) throws IOException {

        String uploadDir = "/Users/seungtae/IdeaProjects/TradeOn/banner";

        String fileName = UUID.randomUUID() + "_" +bannerDto.getImage().getOriginalFilename();

        File destfile = new File(uploadDir, fileName);

        bannerDto.getImage().transferTo(destfile);

        String savePath = "/banner/" + fileName;

        Banner banner = new Banner(savePath);

        bannerRepository.save(banner);
    }

    public List<BannerDto> getBanners() {
        return bannerRepository.findAll().stream().map(BannerDto::setForm).toList();
    }
}
