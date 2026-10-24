package com.springmasterclass.study.dto.response;

public record OrderItemResponse(
        Long id,
        ProductResponse productResponse,
        int quantity,
        double sellPrice
) {
}
