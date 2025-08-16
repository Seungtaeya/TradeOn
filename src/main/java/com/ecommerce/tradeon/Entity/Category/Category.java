package com.ecommerce.tradeon.Entity.Category;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;


    public Category(String name, Category parent) {
        this.name = name;
        assignParentCategory(parent);
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeParentName(Category parent) {
        this.parent = parent;
    }

    private void assignParentCategory(Category category) {
        this.parent = category;
    }
}
