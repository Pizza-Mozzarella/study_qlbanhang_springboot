package com.springmasterclass.study.dto.response;

public record OrderItemResponse(
        Long id,
        ProductResponse product,
        int quantity,
        double sellPrice
) {
}
