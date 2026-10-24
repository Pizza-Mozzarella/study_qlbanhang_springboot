package com.springmasterclass.study.dto.request;

import com.springmasterclass.study.dto.response.OrderItemResponse;
import com.springmasterclass.study.dto.response.UserResponse;

import java.util.List;

public record OrderRequest (
        String userId,
        String status,
        List<OrderItemRequest> orderItemList
){
}
