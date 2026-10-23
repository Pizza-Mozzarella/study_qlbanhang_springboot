package com.springmasterclass.study.dto.request;

import com.springmasterclass.study.dto.response.UserResponse;

public record OrderItemRequest(
        Long productId,
        int quantity,
        double sellPrice
) {
}
