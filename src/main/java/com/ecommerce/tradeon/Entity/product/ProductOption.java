package com.ecommerce.tradeon.Entity.product;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOption {

    @Id @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product_id;

    private String name;
    private String value;
}
