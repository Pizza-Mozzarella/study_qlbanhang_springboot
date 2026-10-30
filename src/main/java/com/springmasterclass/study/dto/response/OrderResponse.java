package com.springmasterclass.study.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponse(
        Long id,
        UserResponse user,
        // chỉnh sau
        String status,
        BigDecimal totalPrice,
        List<OrderItemResponse> orderItemList
) {
}
