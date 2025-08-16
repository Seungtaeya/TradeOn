package com.ecommerce.tradeon.Repository;

import com.ecommerce.tradeon.Dto.Product.ProductDto;

import java.util.List;

public interface ProductHomeQueryRepository {

    List<ProductDto> findPopulars(int limit);
    List<ProductDto> findMostView(int limit);
    List<ProductDto> findUsed(int limit);
    List<ProductDto> findNewProduct(int limit);
}
