package com.ecommerce.tradeon.Controller.myPage;

import com.ecommerce.tradeon.Dto.Category.CategoryDto;
import com.ecommerce.tradeon.Dto.Member.myPageMemberDto;
import com.ecommerce.tradeon.Dto.Order.OrderDto;
import com.ecommerce.tradeon.Dto.Order.SalesOrderDto;
import com.ecommerce.tradeon.Dto.Product.ProductDto;
import com.ecommerce.tradeon.Dto.Session.SessionMember;
import com.ecommerce.tradeon.Service.CategoryService;
import com.ecommerce.tradeon.Service.MemberService;
import com.ecommerce.tradeon.Service.OrderService;
import com.ecommerce.tradeon.Service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyPageController {

    private final MemberService memberService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final OrderService orderService;
    @GetMapping("/mypage")
    public String myPage(HttpSession session, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
        SessionMember loginMember = (SessionMember) session.getAttribute("loginMember");
        loginCheck(loginMember);

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

    @PutMapping("/mypage/product/{id}/edit")
    @ResponseStatus(value = HttpStatus.OK)
    public String editProduct(@PathVariable(name = "id")Long productId,
                              @RequestBody ProductDto productDto, List<MultipartFile> images) {

        System.out.println("productDto.getCategory_id() = " + productDto.getCategory_id());
        productService.modifyProduct(productId,productDto,images);
        return "redirect:/mypage";
    }

    @DeleteMapping("/mypage/product/{id}/delete")
    public String removeProduct(@PathVariable(name = "id")Long productId) {
        productService.deleteProduct(productId);
        return "redirect:/mypage";
    }

    @GetMapping("/mypage/orders")
    public String mypageOrders(Model model, HttpSession session) {
        SessionMember loginMember = (SessionMember) session.getAttribute("loginMember");
        loginCheck(loginMember);

        List<OrderDto> all = orderService.findAll(loginMember.getId());

        model.addAttribute("orders",all);
        return "Member/myPageOrders";
    }

    @GetMapping("/mypage/sales/orders")
    public String salesOrder(HttpSession session, Model model) {

        SessionMember loginMember = (SessionMember) session.getAttribute("loginMember");

        loginCheck(loginMember);

        List<SalesOrderDto> salesOrderAll = orderService.findSalesOrderAll(loginMember.getId());
        model.addAttribute("salesOrders", salesOrderAll);
        return "Member/SalesOrder";
    }

    private String loginCheck(SessionMember loginMember) {

        if (loginMember == null) {
            return "redirect:/login";
        }
        return null;
    }
}
