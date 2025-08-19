package com.ecommerce.tradeon.Repository;

import com.ecommerce.tradeon.Dto.Product.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductCategoryQueryRepository {

    Page<ProductDto> findByCategoryId(Long categoryId, Pageable pageable);

    Page<ProductDto> findProductsInCategoryTree(Long rootCategoryId, Pageable pageable);
}
