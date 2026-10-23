package com.springmasterclass.study.dto.response;

import java.util.List;

public record OrderResponse(
        UserResponse userResponse,
        // chỉnh sau
        String status,
        double totalPrice,
        List<OrderItemResponse> orderItemResponsemList
) {
}
