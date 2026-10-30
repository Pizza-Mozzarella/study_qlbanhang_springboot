package com.springmasterclass.study.dto.response;

import java.math.BigDecimal;

public record OrderItemResponse(
        Long id,
        ProductResponse product,
        int quantity,
        BigDecimal priceAtPurchase
) {
}
