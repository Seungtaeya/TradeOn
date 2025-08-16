package com.ecommerce.tradeon.Repository;

import com.ecommerce.tradeon.Dto.Product.ProductDto;
import com.ecommerce.tradeon.Entity.Order.QOrderItem;
import com.ecommerce.tradeon.Entity.product.Product;
import com.ecommerce.tradeon.Entity.product.QProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductHomeQueryRepositoryImpl implements ProductHomeQueryRepository{

    private final JPAQueryFactory jpaQueryFactory;
    private final QProduct product = QProduct.product;
    private final QOrderItem oi = QOrderItem.orderItem;

    @Override
    public List<ProductDto> findPopulars(int limit) {

        List<Product> fetch = jpaQueryFactory
                .select(product)
                .from(oi)
                .join(product)
                .on(oi.product.id.eq(product.id))
                .groupBy(oi.product.id)
                .orderBy(product.id.count().desc())
                .fetch();

        return fetch.stream().map(ProductDto::setForm).toList();
    }

    @Override
    public List<ProductDto> findMostView(int limit) {
        List<Product> fetch = jpaQueryFactory
                .select(product)
                .from(product)
                .orderBy(product.viewCount.desc(), product.create_At.desc())
                .limit(limit)
                .fetch();

        return fetch.stream().map(ProductDto::setForm).toList();
    }

    @Override
    public List<ProductDto> findUsed(int limit) {
        List<Product> fetch = jpaQueryFactory
                .selectFrom(product)
                .where(product.isUsed)
                .orderBy(product.create_At.desc())
                .fetch();

        return fetch.stream().map(ProductDto::setForm).toList();
    }

    @Override
    public List<ProductDto> findNewProduct(int limit) {
        List<Product> fetch = jpaQueryFactory
                .selectFrom(product)
                .where(product.isUsed.not())
                .orderBy(product.create_At.desc())
                .fetch();

        return fetch.stream().map(ProductDto::setForm).toList();
    }
}
