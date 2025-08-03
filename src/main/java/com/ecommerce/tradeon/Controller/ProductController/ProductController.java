package com.ecommerce.tradeon.Controller.ProductController;

import com.ecommerce.tradeon.Dto.Category.CategoryDto;
import com.ecommerce.tradeon.Dto.Product.ProductDto;
import com.ecommerce.tradeon.Dto.Review.ReviewDto;
import com.ecommerce.tradeon.Dto.Session.SessionMember;
import com.ecommerce.tradeon.Entity.product.Product;
import com.ecommerce.tradeon.Service.CategoryService;
import com.ecommerce.tradeon.Service.ProductImageService;
import com.ecommerce.tradeon.Service.ProductService;
import com.ecommerce.tradeon.Service.ReviewService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService  categoryService;
    private final ProductImageService productImageService;
    private final ReviewService reviewService;

    @GetMapping("/product/new")
    public String createProduct(Model model, HttpSession session) {
        SessionMember loginMember = (SessionMember) session.getAttribute("loginMember");
        if (loginMember == null) {
            return "redirect:/login";
        }
        List<CategoryDto> categoryDtos = categoryService.listAll();

        model.addAttribute("categories", categoryDtos);
        return "Product/productForm";
    }

    @PostMapping("/product/new")
    public String saveProduct(@Valid ProductDto productDto,
                              @RequestParam(name = "images") List<MultipartFile> image) {

        Product product = productService.createProduct(productDto, image);

        return "redirect:/product/detail/" + product.getId();
    }

    @GetMapping("/product/detail/{id}")
    public String productDetail(@PathVariable(name = "id")Long productId, Model model) {
        ProductDto productOne = productService.getProductOne(productId);
        CategoryDto categoryOne = categoryService.getCategoryOne(productOne.getCategory_id());
        List<ReviewDto> reviews = reviewService.findAllReviews();

        model.addAttribute("reviews", reviews);
        model.addAttribute("category",categoryOne);
        model.addAttribute("product",productOne);

        return "Product/productDetail";
    }

}
