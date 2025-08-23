package com.ecommerce.tradeon.Repository;

import com.ecommerce.tradeon.Dto.Product.ProductDto;
import com.ecommerce.tradeon.Entity.Category.QCategory;
import com.ecommerce.tradeon.Entity.product.Product;
import com.ecommerce.tradeon.Entity.product.QProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class ProductCategoryQueryRepositoryImpl implements ProductCategoryQueryRepository{

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;
    QCategory category = QCategory.category;
    QProduct product = QProduct.product;

    @Override
    public Page<ProductDto> findByCategoryId(Long categoryId, Pageable pageable) {

        List<Product> fetch = jpaQueryFactory
                .select(product)
                .from(product)
                .leftJoin(product.category, category).fetchJoin()
                .where(category.id.eq(categoryId))
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

        long total = l != null ? l : 0L;

        return new PageImpl<>(list,pageable,total);
    }

    @Override
    public Page<ProductDto> findProductsInCategoryTree(Long rootCategoryId, Pageable pageable) {
        String sql = "with recursive category_tree as( select id from category where id = :rootCategoryId " +
                "union all " +
                "select c.id from category c join category_tree t on c.parent_id = t.id)" +
                "select id from category_tree";

        List rootCategoryId1 = em.createNativeQuery(sql).setParameter("rootCategoryId", rootCategoryId).getResultList();

        List<Product> fetch = jpaQueryFactory
                .select(product)
                .from(product)
                .leftJoin(product.category,category).fetchJoin()
                .where(category.id.in(rootCategoryId1))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<ProductDto> list = fetch.stream().map(ProductDto::setForm).toList();

        Long l = jpaQueryFactory
                .select(product.count())
                .from(product)
                .leftJoin(product.category, category)
                .where(category.id.in(rootCategoryId1))
                .fetchOne();

        long total = l != null ? l : 0L;

        return new PageImpl<>(list, pageable, total);

    }


}
