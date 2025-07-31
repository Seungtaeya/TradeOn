package com.ecommerce.tradeon.Controller.myPage;

import com.ecommerce.tradeon.Dto.Category.CategoryDto;
import com.ecommerce.tradeon.Dto.Member.myPageMemberDto;
import com.ecommerce.tradeon.Dto.Product.ProductDto;
import com.ecommerce.tradeon.Dto.Session.SessionMember;
import com.ecommerce.tradeon.Entity.Member.Member;
import com.ecommerce.tradeon.Service.CategoryService;
import com.ecommerce.tradeon.Service.MemberService;
import com.ecommerce.tradeon.Service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.http.HttpRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyPageController {

    private final MemberService memberService;
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/mypage")
    public String myPage(HttpSession session, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
        SessionMember loginMember = (SessionMember) session.getAttribute("loginMember");

        if (loginMember == null) {
            redirectAttributes.addFlashAttribute("uri",request.getRequestURI());
            return "redirect:/login";
        }

        myPageMemberDto myPageMember = memberService.findMyPageMember(loginMember.getId());
        List<ProductDto> productDtos = productService.MemberProduct(loginMember.getId());

        model.addAttribute("member",myPageMember);
        model.addAttribute("myProducts", productDtos);
        return "Member/mypage";
    }

    @GetMapping("/mypage/product/{id}")
    public String modifyProduct(@PathVariable(name = "id")Long productId, Model model) {
        ProductDto productOne = productService.getProductOne(productId);

        model.addAttribute("product", productOne);

        return "Member/myProductDetail";
    }

    @GetMapping("/mypage/product/{id}/edit")
    public String editProduct(@PathVariable(name = "id")Long productId, Model model) {
        ProductDto productOne = productService.getProductOne(productId);
        List<CategoryDto> categories = categoryService.listAll();

        model.addAttribute("categories",categories);
        model.addAttribute("product", productOne);
        return "Member/myPageEdit";
    }

    @PostMapping("/mypage/product/{id}/edit")
    public String editProduct(@PathVariable(name = "id")Long productId,ProductDto productDto, List<MultipartFile> images) {

        productService.modifyProduct(productId,productDto,images);
        return "redirect:/product/detail/" + productId;
    }
}
