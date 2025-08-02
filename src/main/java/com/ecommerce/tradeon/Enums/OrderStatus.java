package com.ecommerce.tradeon.Enums;

import jakarta.persistence.Embeddable;

public enum OrderStatus {
    PENDING,
    PAID,
    SHIPPED,
    DELEVERED,
    CANCELLED,
    REFUNDED
}
