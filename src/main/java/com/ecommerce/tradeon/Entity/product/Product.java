package com.ecommerce.tradeon.Entity.product;

import com.ecommerce.tradeon.Entity.Cart.Cart;
import com.ecommerce.tradeon.Entity.Cart.CartItem;
import com.ecommerce.tradeon.Entity.Category.Category;
import com.ecommerce.tradeon.Entity.Image.ProductImage;
import com.ecommerce.tradeon.Entity.Member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor()
public class Product {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member seller_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    List<CartItem> cart = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> images = new ArrayList<>();

    private String title;
    private String description;
    private int price;
    private int stock;
    private boolean isUsed;
    private LocalDateTime create_At;

    public void setProduct(String title, String description, int price, int stock, boolean isUsed) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.isUsed = isUsed;
    }

    public void assignMember(Member member) {
        this.seller_id = member;
    }

    public void assignCategory(Category category) {
        this.category = category;
    }

    public void assignProductImages(ProductImage productImage) {
        this.images.add(productImage);
        productImage.assignProduct(this);
    }

    public void changeTitle (String title) {
        this.title = title;
    }

    public void changeDescription (String description) {
        this.description = description;
    }

    public void changePrice (int price) {
        this.price = price;
    }

    public void changeStock (int stock) {
        this.stock = stock;
    }

    public void changeUsed(boolean uesed) {
        this.isUsed = uesed;
    }

    public void changeCategory(Category category) {
        this.category = category;
    }


    @PrePersist
    private void CreateProductTime() {
        this.create_At = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && stock == product.stock && isUsed == product.isUsed && Objects.equals(id, product.id) && Objects.equals(seller_id, product.seller_id) && Objects.equals(category, product.category) && Objects.equals(cart, product.cart) && Objects.equals(images, product.images) && Objects.equals(title, product.title) && Objects.equals(description, product.description) && Objects.equals(create_At, product.create_At);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seller_id, category, cart, images, title, description, price, stock, isUsed, create_At);
    }
}
