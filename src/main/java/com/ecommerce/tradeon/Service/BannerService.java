package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Dto.Banner.BannerDto;
import com.ecommerce.tradeon.Entity.Banner.Banner;
import com.ecommerce.tradeon.Exceptions.CustomBannerException;
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
    public void saveBanner(BannerDto bannerDto) {

        String uploadDir = "/Users/seungtae/IdeaProjects/TradeOn/banner";

        String fileName = UUID.randomUUID() + "_" +bannerDto.getImage().getOriginalFilename();

        File destfile = new File(uploadDir, fileName);

        try {
            bannerDto.getImage().transferTo(destfile);

        } catch (IOException e) {
            throw new CustomBannerException(e.getMessage());
        }

        String savePath = "/banner/" + fileName;

        Banner banner = new Banner(savePath, bannerDto.getImageName());


        Banner save = bannerRepository.save(banner);
    }

    public List<BannerDto> getBanners() {
        return bannerRepository.findAll().stream().map(BannerDto::setForm).toList();
    }

    public BannerDto getBanner(Long bannerId) {
        Banner banner = bannerRepository.findById(bannerId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이벤트 입니다."));
        return BannerDto.setForm(banner);
    }

    @Transactional
    public void deleteBanner(Long bannerId) {
        Banner banner = bannerRepository.findById(bannerId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이벤트 입니다."));

        bannerRepository.delete(banner);
    }
}
