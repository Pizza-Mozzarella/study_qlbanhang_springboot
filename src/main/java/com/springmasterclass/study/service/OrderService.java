package com.springmasterclass.study.service;

import com.springmasterclass.study.dto.request.OrderRequest;
import com.springmasterclass.study.dto.response.OrderResponse;

public interface OrderService {
    public OrderResponse createOrder(OrderRequest orderRequest);

    public OrderResponse getOrderById(Long id);
    public OrderResponse updateOrderById(Long id, OrderRequest request);
    public void deleteOrderById(Long id);


}
