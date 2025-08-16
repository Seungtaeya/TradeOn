package com.ecommerce.tradeon.Controller.Admin;

import com.ecommerce.tradeon.Dto.Category.CategoryDto;
import com.ecommerce.tradeon.Dto.Product.ProductDto;
import com.ecommerce.tradeon.Entity.Category.Category;
import com.ecommerce.tradeon.Service.CategoryService;
import com.ecommerce.tradeon.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping("/admin/category")
    public String Category(Model model) {

        // 모든 카테고리 보여주기용
        List<CategoryDto> categoryDtos = categoryService.listAll();

        // 상위 카테고리 선택용
        List<CategoryDto> list = categoryDtos.stream().filter(categoryDto -> categoryDto.getParent() == null)
                .toList();

        model.addAttribute("allCategories",list);
        model.addAttribute("categories", categoryDtos);
        return "Admin/Category/category";
    }

    @GetMapping("category/{id}")
    public String listCategoryProducts(@PathVariable(name = "id") Long categoryId, Model model) {
        Page<ProductDto> productByCategoryId = productService.findProductByCategoryId(categoryId, PageRequest.of(0, 10));
        CategoryDto categoryOne = categoryService.getCategoryOne(categoryId);

        model.addAttribute("category",categoryOne);
        model.addAttribute("products", productByCategoryId);

        return "Product/list";
    }

    @PostMapping("/admin/categories")
    public String SaveCategory(CategoryDto categoryDto, RedirectAttributes redirectAttributes) {

        System.out.println("categoryDto.getName() = " + categoryDto.getName());
        if(categoryDto.getName().isBlank() ) {
            redirectAttributes.addFlashAttribute("errorMessage","카테고리 이름을 입력해주세요");
            return "redirect:/admin/category" ;
        }

        categoryService.createCategory(categoryDto);
        return "redirect:/admin/category";
    }

    @PostMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable(name = "id")Long categoryId){
        categoryService.remove(categoryId);

        return "redirect:/admin/category";
    }

    @GetMapping("/admin/categories/modify/{id}")
    public String modifyCategory(@PathVariable(name = "id") Long categoryId, Model model) {
        CategoryDto categoryOne = categoryService.getCategoryOne(categoryId);

        // 모든 카테고리 보여주기용
        List<CategoryDto> categoryDtos = categoryService.listAll();
        // 상위 카테고리 선택용
        List<CategoryDto> list = categoryDtos.stream().filter(categoryDto -> categoryDto.getParent() == null)
                .toList();

        model.addAttribute("allCategories",list);

        model.addAttribute("category",categoryOne);
        return "Admin/Category/categoryModify";
    }

    @PostMapping("/admin/categories/modify/{id}")
    public String modifyCategory(@PathVariable(name = "id") Long categoryId, CategoryDto categoryDto) {


        categoryService.modify(categoryId, categoryDto);
        return "redirect:/admin/category";
    }
}
