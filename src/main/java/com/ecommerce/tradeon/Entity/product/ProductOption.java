package com.ecommerce.tradeon.Entity.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class ProductOption {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private String name;
    private String optionValue;

    public ProductOption(String name, String value) {
        this.name = name;
        this.optionValue = value;
    }

    public void addProduct(Product product) {
        this.product = product;
        product.getOptions().add(this);
    }
}
