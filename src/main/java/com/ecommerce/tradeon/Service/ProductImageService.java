package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Entity.Image.ProductImage;
import com.ecommerce.tradeon.Repository.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ProductImageService {

    private final ProductImageRepository productImageRepository;


        String savePath = null;

        if(!image.isEmpty()) {
                String uploadDir = "/Users/seungtae/IdeaProjects/TradeOn/uploads";



                image.transferTo(destfile);

                savePath = "/uploads/" + fileName;
                ProductImage productImage = new ProductImage(savePath);

        return null;
    }
}
