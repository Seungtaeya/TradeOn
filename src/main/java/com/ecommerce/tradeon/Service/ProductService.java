package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Dto.Product.ProductDto;
import com.ecommerce.tradeon.Entity.Category.Category;
import com.ecommerce.tradeon.Entity.Image.ProductImage;
import com.ecommerce.tradeon.Entity.Member.Member;
import com.ecommerce.tradeon.Entity.product.Product;
import com.ecommerce.tradeon.Repository.ProductImageRepository;
import com.ecommerce.tradeon.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImageService  productImageService;
    private final MemberService memberService;
    private final CategoryService categoryService;


    @Transactional

        Member seller = memberService.findByMemberId(productDto.getSeller_id());
        Category category = categoryService.getCategoryEntity(productDto.getCategory_id());
        Product product = new Product();
        product.assignMember(seller);
        product.assignCategory(category);

        product.setProduct(productDto.getTitle(),productDto.getDescription(),productDto.getPrice(), productDto.getStock(), productDto.getIsUsed());

        return productRepository.save(product);
    }

    public ProductDto getProductOne(Long productId) {
        Product product = getProduct(productId);

        return ProductDto.setForm(product);
    }
    
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(ProductDto::setForm)
                .toList();
    }

    @Transactional
        Product product = getProduct(productId);

        product.changeTitle(productDto.getTitle());
        product.changeDescription(productDto.getDescription());
        product.changePrice(productDto.getPrice());
        product.changeStock(productDto.getStock());

    }

    @Transactional
    public void deleteProduct(Long productId) {
        Product product = getProduct(productId);
        productRepository.delete(product);
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("선택하신 제품이 존재 하지 않습니다."));
    }
}
