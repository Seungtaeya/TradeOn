package com.ecommerce.tradeon.Entity.Image;

import com.ecommerce.tradeon.Entity.product.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private String imageUrl;

    public ProductImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void assignProduct(Product product) {
        this.product = product;
    }
}
