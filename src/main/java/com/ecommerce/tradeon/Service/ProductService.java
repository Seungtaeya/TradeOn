package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Dto.Category.CategoryDto;
import com.ecommerce.tradeon.Dto.Product.ProductDto;
import com.ecommerce.tradeon.Dto.Product.ProductOptionDto;
import com.ecommerce.tradeon.Dto.Search.ProductSearchCondition;
import com.ecommerce.tradeon.Dto.Vo.RegionDto;
import com.ecommerce.tradeon.Entity.Category.Category;
import com.ecommerce.tradeon.Entity.Image.ProductImage;
import com.ecommerce.tradeon.Entity.Member.Member;
import com.ecommerce.tradeon.Entity.product.Product;
import com.ecommerce.tradeon.Entity.product.ProductOption;
import com.ecommerce.tradeon.Entity.vo.Region;
import com.ecommerce.tradeon.Repository.ProductOptionRepository;
import com.ecommerce.tradeon.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
    public Product createProduct(ProductDto productDto, RegionDto regionDto, List<MultipartFile> image) {

        Member seller = memberService.findByMemberId(productDto.getSeller_id());
        Category category = categoryService.getCategoryEntity(productDto.getCategory_id());
        Product product = new Product();
        product.assignMember(seller);
        product.assignCategory(category);

        product.setProduct(productDto.getTitle(),productDto.getDescription(),productDto.getPrice(), productDto.getStock(), productDto.getIsUsed());

        if(productDto.getIsUsed() == true && regionDto != null) {
            product.addRegion(new Region(regionDto.getSido(),regionDto.getSigun(),regionDto.getDong()));
        } else {
            product.addRegion(null);
        }

        if(image != null) {
            for (MultipartFile multipartFile : image) {
                ProductImage productImage = productImageService.createImage(multipartFile);

                product.assignProductImages(productImage);

            }
        }

        if(productDto.getOptions() != null && !productDto.getOptions().isEmpty()) {
            for (ProductOptionDto option : productDto.getOptions()) {
                ProductOption po = new ProductOption(option.getName(), option.getOptionValue());
                po.addProduct(product);
                productOptionRepository.save(po);
            }
        }

        return productRepository.save(product);
    }

    public Page<ProductDto> findProductByCategoryId(Long categoryId, Pageable pageable) {

        CategoryDto categoryOne = categoryService.getCategoryOne(categoryId);

        if(categoryOne.getParentId() == 0L) {
            return productRepository.findProductsInCategoryTree(categoryId,pageable);
        }

        return productRepository.findByCategoryId(categoryId, pageable);
    }

    public Page<ProductDto> searchProduct(ProductSearchCondition cond, Pageable pageable) {
        return productRepository.ProductSearchCondition(cond, pageable);
    }

    public ProductDto getProductOne(Long productId) {
        Product product = getProduct(productId);

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

    public List<ProductDto> findTopProductByViewCount(int limit) {
        return productRepository.findMostView(limit);
    }

    public List<ProductDto> findTopProductByPopulars(int limit) {
        return productRepository.findPopulars(limit);
    }

    public List<ProductDto> findTopProductByUsed(int limit) {
        return productRepository.findUsed(limit);
    }

    public List<ProductDto> findTop5NewProduct(int limit) {
        return productRepository.findNewProduct(limit);
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
    public void IncrementViewCount(Long productId) {
        Product product = getProduct(productId);

        product.increaseViewCount();
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
