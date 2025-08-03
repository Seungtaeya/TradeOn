package com.ecommerce.tradeon.Repository;


import com.ecommerce.tradeon.Dto.Order.SalesOrderDto;

import java.util.List;

public interface SaleOrderRepository {
    List<SalesOrderDto> findSalesOrderAll(Long memberId);
}
