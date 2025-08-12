package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Dto.Product.ProductDto;
import com.ecommerce.tradeon.Dto.Product.ProductOptionDto;
import com.ecommerce.tradeon.Entity.Category.Category;
import com.ecommerce.tradeon.Entity.Image.ProductImage;
import com.ecommerce.tradeon.Entity.Member.Member;
import com.ecommerce.tradeon.Entity.product.Product;
import com.ecommerce.tradeon.Entity.product.ProductOption;
import com.ecommerce.tradeon.Repository.ProductImageRepository;
import com.ecommerce.tradeon.Repository.ProductOptionRepository;
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
    private final ProductOptionRepository productOptionRepository;
    private final ProductImageService  productImageService;
    private final MemberService memberService;
    private final CategoryService categoryService;


    @Transactional
    public Product createProduct(ProductDto productDto, List<MultipartFile> image) {

        Member seller = memberService.findByMemberId(productDto.getSeller_id());
        Category category = categoryService.getCategoryEntity(productDto.getCategory_id());
        Product product = new Product();
        product.assignMember(seller);
        product.assignCategory(category);

        product.setProduct(productDto.getTitle(),productDto.getDescription(),productDto.getPrice(), productDto.getStock(), productDto.getIsUsed());


        if(image != null) {
            for (MultipartFile multipartFile : image) {
                ProductImage productImage = productImageService.createImage(multipartFile);

                product.assignProductImages(productImage);

            }
        }

        if(!productDto.getOptions().isEmpty()) {
            for (ProductOptionDto option : productDto.getOptions()) {
                ProductOption po = new ProductOption(option.getName(), option.getOptionValue());
                po.addProduct(product);
                productOptionRepository.save(po);
            }
        }

        return productRepository.save(product);
    }

//    public List<String> findProductOptionByOptionName(Long productId, String name) {
//         return productOptionRepository.findByProduct_IdAndName(productId, name);
//    }

    public ProductDto getProductOne(Long productId) {
        Product product = getProduct(productId);

//        for (ProductOption option : product.getOptions()) {

//            List<String> byProductIdAndName = productOptionRepository.findByProduct_IdAndName(productId, option.getName());

//            option.loadOptionValuesFromDB(byProductIdAndName);

//        }

        return ProductDto.setForm(product);
    }
    
    public Product getProductEntity(Long productId) {
        return getProduct(productId);
    }

    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(ProductDto::setForm)
                .toList();
    }

    public List<ProductDto> MemberProduct(Long memberId) {
        return productRepository.findBySeller_id(memberId).stream()
                .map(ProductDto::setForm)
                .toList();
    }


    @Transactional
    public void modifyProduct(Long productId, ProductDto productDto, List<MultipartFile> image) {
        Product product = getProduct(productId);


        Category category = categoryService.getCategoryEntity(productDto.getCategory_id());

        product.changeTitle(productDto.getTitle());
        product.changeDescription(productDto.getDescription());
        product.changePrice(productDto.getPrice());
        product.changeStock(productDto.getStock());
        product.changeUsed(productDto.getIsUsed());
        product.changeCategory(category);

        if(image != null && !image.isEmpty()) {
            for (MultipartFile multipartFile : image) {
                ProductImage productImage = productImageService.createImage(multipartFile);

                if(productImage != null) {
                    product.assignProductImages(productImage);
                }
            }
        }

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
