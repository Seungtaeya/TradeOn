package com.ecommerce.tradeon.Controller.Search;

import com.ecommerce.tradeon.Dto.Product.ProductDto;
import com.ecommerce.tradeon.Dto.Search.ProductSearchCondition;
import com.ecommerce.tradeon.Service.ProductService;
import jakarta.security.auth.message.callback.PrivateKeyCallback;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class SearchController {

    private final ProductService productService;

    @GetMapping("/search")
    public String search(ProductSearchCondition cond, Pageable pageable, Model model) {


        Page<ProductDto> product = productService.searchProduct(cond, pageable);

        model.addAttribute("keyword", cond.getKeyword());
        model.addAttribute("products",product);

        return "Search/Search";
    }
}
