package com.ecommerce.tradeon.Controller;

import com.ecommerce.tradeon.Dto.Category.CategoryDto;
import com.ecommerce.tradeon.Dto.Product.ProductDto;
import com.ecommerce.tradeon.Dto.Session.SessionMember;
import com.ecommerce.tradeon.Service.CategoryService;
import com.ecommerce.tradeon.Service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {


    private final CategoryService categoryService;
    private final ProductService productService;


    @GetMapping("/")
    public String home(Model model) {
        List<CategoryDto> categoryDtos = categoryService.listAll();
        List<ProductDto> all = productService.findAll();
        List<ProductDto> mostViewCount = productService.findTopProductByViewCount(5);
        List<ProductDto> Populars = productService.findTopProductByPopulars(5);
//        List<ProductDto> Used = productService.findTopProductByUsed(5);

        model.addAttribute("popularProducts",Populars);
//        model.addAttribute("usedProduct",Used);
        model.addAttribute("categories", categoryDtos);
        model.addAttribute("latestProducts", all);
        model.addAttribute("mostViewedProducts", mostViewCount);
        return "home";
    }

}
