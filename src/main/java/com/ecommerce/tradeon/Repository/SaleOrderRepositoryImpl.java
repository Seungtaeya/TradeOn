package com.ecommerce.tradeon.Repository;

import com.ecommerce.tradeon.Dto.Order.SalesOrderDto;
import com.ecommerce.tradeon.Entity.Order.Order;
import com.ecommerce.tradeon.Entity.Order.QOrder;
import com.ecommerce.tradeon.Entity.Order.QOrderItem;
import com.ecommerce.tradeon.Entity.product.QProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SaleOrderRepositoryImpl implements SaleOrderRepository{

    private final JPAQueryFactory jpaQueryFactory;
    QOrderItem oi = QOrderItem.orderItem;
    QProduct product = QProduct.product;
    QOrder order = QOrder.order;

    @Override
    public List<SalesOrderDto> findSalesOrderAll(Long memberId) {
        List<Order> fetch = jpaQueryFactory
                .selectFrom(order)
                .join(order.orderItems, oi).fetchJoin()
                .where(product.seller_id.id.eq(memberId))
                .fetch();

        return fetch.stream()
                .map(SalesOrderDto::setForm)
                .toList();
    }

}
