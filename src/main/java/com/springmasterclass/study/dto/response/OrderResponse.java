package com.springmasterclass.study.dto.response;

import java.util.List;

public record OrderResponse(
        Long id,
        UserResponse userResponse,
        // chỉnh sau
        String status,
        double totalPrice,
        List<OrderItemResponse> orderItemResponseList
) {
}
