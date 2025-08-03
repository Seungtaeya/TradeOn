package com.ecommerce.tradeon.Entity.Order;

import com.ecommerce.tradeon.Entity.Member.Member;
import com.ecommerce.tradeon.Enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Orders")
public class Order {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime orderDate;


    public void  assignsMember(Member member) {
        this.member = member;
    }

    public void addOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @PrePersist
    private void orderDate() {
        this.orderDate = LocalDateTime.now();
    }

    public void changeStatus(String status) {
        if ("PROCESSING".equals(status)) {
            this.orderStatus = OrderStatus.PROCESSING;
        } else if ("SHIPPING".equals(status)) {
            this.orderStatus = OrderStatus.SHIPPING;
        } else if ("DELIVERED".equals(status)) {
            this.orderStatus = OrderStatus.DELIVERED;
        } else
            this.orderStatus = OrderStatus.CANCELLED;
    }
}
