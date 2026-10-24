package com.springmasterclass.study.service;

import com.springmasterclass.study.dto.request.OrderRequest;
import com.springmasterclass.study.dto.response.OrderResponse;

public interface OrderService {
    public OrderResponse createOrder(OrderRequest orderRequest);

}
