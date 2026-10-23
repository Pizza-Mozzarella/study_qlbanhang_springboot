package com.springmasterclass.study.dto.response;

public record OrderItemResponse(
        ProductResponse productResponse,
        int quantity,
        double sellPrice
) {
}
