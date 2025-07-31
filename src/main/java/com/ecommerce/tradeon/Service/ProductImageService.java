package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Entity.Image.ProductImage;
import com.ecommerce.tradeon.Entity.product.Product;
import com.ecommerce.tradeon.Repository.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductImageService {

    private final ProductImageRepository productImageRepository;

    @Transactional
    public ProductImage createImage(MultipartFile image) {

        String savePath = null;

        if(!image.isEmpty()) {
            try {
                String uploadDir = "/Users/seungtae/IdeaProjects/TradeOn/uploads";

                String fileName = image.getOriginalFilename();

                File destfile = new File(uploadDir, fileName);

                image.transferTo(destfile);

                savePath = "/uploads/" + fileName;
                ProductImage productImage = new ProductImage(savePath);

                return productImageRepository.save(productImage);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
