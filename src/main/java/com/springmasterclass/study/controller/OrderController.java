package com.springmasterclass.study.controller;


import com.springmasterclass.study.common.ApiResponse;
import com.springmasterclass.study.common.BaseController;
import com.springmasterclass.study.dto.request.OrderRequest;
import com.springmasterclass.study.dto.response.OrderResponse;
import com.springmasterclass.study.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController extends BaseController {
    OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<OrderResponse> create(@RequestParam OrderRequest orderRequest) {
        return createSuccessResponse(orderService.createOrder(orderRequest));
    }


}
