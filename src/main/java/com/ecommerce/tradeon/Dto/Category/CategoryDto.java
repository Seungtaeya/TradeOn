package com.ecommerce.tradeon.Dto.Category;

import com.ecommerce.tradeon.Entity.Category.Category;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Parent;

@Data
@NoArgsConstructor
public class CategoryDto {

    private Long id;
    private String name;
    private Long parentId;
    private CategoryDto parent;

    public static CategoryDto setForm(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setParentId(category.getParent() != null ? category.getParent().getId() : 0L);
        categoryDto.setParent(category.getParent() != null ? new CategoryDto(category.getParent().getName(),category.getParent().getId()) : null);
        return categoryDto;
    }
    public CategoryDto(String name) {
        this.name = name;
    }

    public CategoryDto(String name, Long parentId) {
        this.name = name;
        this.id = parentId;
    }
}
