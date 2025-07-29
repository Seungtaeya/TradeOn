package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Dto.Category.CategoryDto;
import com.ecommerce.tradeon.Entity.Category.Category;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    private Long parentId;
    private Long childId;
    @BeforeEach
    void BeforAll() {
        CategoryDto categoryDto = new CategoryDto("한식");
        Category category = categoryService.createCategory(categoryDto);
        parentId = category.getId();

        CategoryDto ParentCategory = new CategoryDto("김치찌개",category.getId());
        Category category1 = categoryService.createCategory(ParentCategory);
        childId = category1.getId();
    }


    @Test
    public void save() throws Exception {
        //given
        CategoryDto categoryDto = new CategoryDto("가전제품");
        Category category = categoryService.createCategory(categoryDto);

        CategoryDto ParentCategory = new CategoryDto("모니터",category.getId());
        Category category1 = categoryService.createCategory(ParentCategory);
        //when

        //then
        assertThat(category.getName()).isEqualTo(categoryDto.getName());
        assertThat(category1.getParent().getId()).isEqualTo(ParentCategory.getParentId());
        assertThat(ParentCategory.getName()).isEqualTo(category1.getName());

        List<CategoryDto> categoryDtos = categoryService.listAll();
        assertThat(categoryDtos.size()).isEqualTo(4);
    }

    @Test
    public void listAll() throws Exception {
        //given
        //when
        List<CategoryDto> categoryDtos = categoryService.listAll();
        //then
        assertThat(categoryDtos.size()).isEqualTo(2);
    }

    @Test
    public void update() throws Exception {
        //given
        CategoryDto categoryOne = categoryService.getCategoryOne(parentId);
        System.out.println("categoryOne.getName() = " + categoryOne.getName());
        //when
        categoryOne.setName("중식");
        categoryService.modify(categoryOne.getId(),categoryOne);

        CategoryDto categoryOne1 = categoryService.getCategoryOne(parentId);
        //then
        assertThat(categoryOne1.getName()).isEqualTo("중식");
    }

    @Test
    public void delete() throws Exception {
        //given
        CategoryDto categoryOne1 = categoryService.getCategoryOne(childId);
        System.out.println("categoryOne.getName() = " + categoryOne1.getName());
        //when
        categoryService.remove(categoryOne1.getId());
        List<CategoryDto> categoryCount = categoryService.listAll();
        //then
        assertThat(categoryCount.size()).isEqualTo(1);
        assertThrows(IllegalArgumentException.class, () -> categoryService.getCategoryOne(2L),"존재하지 않는 카테고리입니다.");
    }
}