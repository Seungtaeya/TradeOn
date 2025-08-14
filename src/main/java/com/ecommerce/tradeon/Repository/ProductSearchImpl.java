package com.ecommerce.tradeon.Repository;

import com.ecommerce.tradeon.Dto.Product.ProductDto;
import com.ecommerce.tradeon.Dto.Search.ProductSearchCondition;
import com.ecommerce.tradeon.Entity.product.Product;
import com.ecommerce.tradeon.Entity.product.QProduct;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class ProductSearchImpl implements ProductSearch{

    private final JPAQueryFactory jpaQueryFactory;

    QProduct product = QProduct.product;

    @Override
    public Page<ProductDto> ProductSearchCondition(ProductSearchCondition cond, Pageable pageable) {
        List<Product> fetch = jpaQueryFactory
                .selectFrom(product)
                .where(ContainsKeyword(cond.getKeyword()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<ProductDto> list = fetch.stream()
                .map(ProductDto::setForm)
                .toList();

        Long total = jpaQueryFactory
                .select(product.count())
                .from(product)
                .where(ContainsKeyword(cond.getKeyword()))
                .fetchOne();

        long totalCount = total != null ? total : 0L;

        return new PageImpl<>(list, pageable, totalCount);
    }

    public BooleanExpression ContainsKeyword(String keyword) {
        if (keyword == null || keyword.isBlank()) return null;

        return product.title.containsIgnoreCase(keyword)
                .or(product.region.sido.containsIgnoreCase(keyword))
                .or(product.region.sigun.containsIgnoreCase(keyword))
                .or(product.region.dong.containsIgnoreCase(keyword));
    }
}
