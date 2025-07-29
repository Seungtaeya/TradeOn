package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Dto.Category.CategoryDto;
import com.ecommerce.tradeon.Entity.Category.Category;
import com.ecommerce.tradeon.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Category createCategory(CategoryDto categoryDto) {
        Category parentcategory = null;

        if (categoryDto.getParentId() != null) {
            parentcategory = categoryRepository.findById(categoryDto.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("잘못된 상위 카테고리 입니다."));

        }

        Category category = new Category(categoryDto.getName(),parentcategory);

        return categoryRepository.save(category);

    }

    public List<CategoryDto> listAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryDto::setForm)
                .toList();
    }

    public Category getCategoryEntity(Long categoryId) {
        return getCategory(categoryId);
    }

    public CategoryDto getCategoryOne(Long categoryId) {
        Category category = getCategory(categoryId);

        return CategoryDto.setForm(category);
    }

    @Transactional
    public void modify(Long categoryId, CategoryDto categoryDto) {
        Category category = getCategory(categoryId);

        category.changeName(categoryDto.getName());
    }

    @Transactional
    public void remove(Long categoryId) {
        Category category = getCategory(categoryId);

        categoryRepository.delete(category);
    }

    private Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));
    }
}
