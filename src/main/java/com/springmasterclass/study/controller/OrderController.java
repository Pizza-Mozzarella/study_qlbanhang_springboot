package com.springmasterclass.study.controller;


import com.springmasterclass.study.common.ApiResponse;
import com.springmasterclass.study.common.BaseController;
import com.springmasterclass.study.dto.request.OrderRequest;
import com.springmasterclass.study.dto.response.OrderResponse;
import com.springmasterclass.study.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController extends BaseController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<OrderResponse> create(@Valid @RequestBody OrderRequest orderRequest) {
        return createSuccessResponse(orderService.createOrder(orderRequest));
    }

    @GetMapping("/{id}")
    public ApiResponse<OrderResponse> show(@PathVariable Long id) {
        return createSuccessResponse(orderService.getOrderById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<OrderResponse> update(@PathVariable Long id, @Valid @RequestBody OrderRequest orderRequest) {
        return createSuccessResponse(orderService.updateOrderById(id, orderRequest));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return createSuccessResponse("Đã xóa thành công đơn hàng có id: " + id);
    }

}
