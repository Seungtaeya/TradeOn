package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Dto.Order.OrderItemOptionDto;
import com.ecommerce.tradeon.Entity.Order.OrderItem;
import com.ecommerce.tradeon.Entity.Order.OrderItemOption;
import com.ecommerce.tradeon.Repository.OrderItemOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderItemOptionService {

    private final OrderItemOptionRepository orderItemOptionRepository;

    @Transactional
    public OrderItemOption saveOrderItemOption(OrderItemOptionDto dto, OrderItem item) {
        OrderItemOption option = new OrderItemOption();
        option.updateFromDto(dto);
        option.addOrderItem(item);
        return orderItemOptionRepository.save(option);
    }
}
