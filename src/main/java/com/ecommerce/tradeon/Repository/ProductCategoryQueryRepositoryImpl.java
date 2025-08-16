package com.ecommerce.tradeon.Repository;

import com.ecommerce.tradeon.Dto.Product.ProductDto;
import com.ecommerce.tradeon.Entity.Category.QCategory;
import com.ecommerce.tradeon.Entity.product.Product;
import com.ecommerce.tradeon.Entity.product.QProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class ProductCategoryQueryRepositoryImpl implements ProductCategoryQueryRepository{

    private final JPAQueryFactory jpaQueryFactory;
    QCategory category = QCategory.category;
    QProduct product = QProduct.product;

    @Override
    public Page<ProductDto> findByCategoryId(Long categoryId, Pageable pageable) {

        List<Product> fetch = jpaQueryFactory
                .select(product)
                .from(product)
                .where(product.category.id.eq(categoryId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(product.create_At.desc())
                .fetch();

        List<ProductDto> list = fetch.stream().map(ProductDto::setForm).toList();

        Long l = jpaQueryFactory
                .select(product.count())
                .from(product)
                .where(product.category.id.eq(categoryId))
                .fetchOne();

        Long total = l != null ? l : 0L;

        return new PageImpl<>(list,pageable,total);
    }
}
