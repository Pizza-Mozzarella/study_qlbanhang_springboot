package com.springmasterclass.study.service.impl;

import com.springmasterclass.study.dto.request.OrderRequest;
import com.springmasterclass.study.dto.response.OrderResponse;
import com.springmasterclass.study.mapper.OrderMapper;
import com.springmasterclass.study.model.Order;
import com.springmasterclass.study.repository.OrderRepository;
import com.springmasterclass.study.service.BaseCrudService;
import com.springmasterclass.study.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl extends BaseCrudService<Order, Long, OrderRequest, OrderResponse>
        implements OrderService  {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;



    @Override
    protected JpaRepository<Order, Long> getRepository() {
        return orderRepository;
    }

    @Override
    protected Order toEntity(OrderRequest request) {
        return orderMapper.toEntity(request);
    }

    @Override
    protected void updateFromRequest(OrderRequest request, Order entity) {

    }

    @Override
    protected OrderResponse toResponse(Order entity) {
        return orderMapper.toResponse(entity);
    }

    @Override
    protected String getEntityName() {
        return "Order";
    }

    @Override
    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest) {
        return create(orderRequest);
    }



}
