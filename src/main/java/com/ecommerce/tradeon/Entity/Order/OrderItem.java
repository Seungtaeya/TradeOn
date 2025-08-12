package com.ecommerce.tradeon.Entity.Order;

import com.ecommerce.tradeon.Entity.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class OrderItem {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "orderItem")
    private List<OrderItemOption> option;

    private int price;

    public void assignOrder(Order order) {
        this.order = order;
        order.getOrderItems().add(this);
    }

    public void assignProduct(Product product) {
        this.product = product;
    }

    public void addPrice(int price) {
        this.price = price;
    }

    public void deleteQuantity() {
        product.changeStock(product.getStock() - 1);
    }
}
