package com.springmasterclass.study.service.impl;

import com.springmasterclass.study.dto.request.OrderItemRequest;
import com.springmasterclass.study.dto.request.OrderRequest;
import com.springmasterclass.study.dto.response.OrderResponse;
import com.springmasterclass.study.exception.exception.NotFoundException;
import com.springmasterclass.study.mapper.OrderItemMapper;
import com.springmasterclass.study.mapper.OrderMapper;
import com.springmasterclass.study.model.Order;
import com.springmasterclass.study.model.OrderItem;
import com.springmasterclass.study.model.Product;
import com.springmasterclass.study.model.User;
import com.springmasterclass.study.repository.OrderRepository;
import com.springmasterclass.study.repository.ProductRepository;
import com.springmasterclass.study.repository.UserRepository;
import com.springmasterclass.study.service.BaseCrudService;
import com.springmasterclass.study.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl extends BaseCrudService<Order, Long, OrderRequest, OrderResponse>
        implements OrderService  {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    private final UserRepository userRepository;
    private final ProductRepository productRepository;



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
    protected void enrich(OrderRequest orderRequest, Order order) {
        checkUserExist(orderRequest, order);
        calculateTotalPrice(orderRequest, order);
        updateStatus(order);
    }

    @Override
    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest) {
        return create(orderRequest);
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findWithDetailsById(id)
                .orElseThrow(() -> new NotFoundException("Order not found with id: " + id));
        return orderMapper.toResponse(order);
    }

    @Override
    @Transactional
    public OrderResponse updateOrderById(Long id, OrderRequest request) {
        return updateById(id, request);
    }

    @Override
    @Transactional
    public void deleteOrderById(Long id) {
        deleteById(id);
    }

    // helper của order

    void checkUserExist(OrderRequest orderRequest, Order order) {
        User user = userRepository.findById(orderRequest.userId())
                .orElseThrow(() -> new NotFoundException("Khong tim thay User voi id: " + orderRequest.userId()));
        order.setUser(user);
    }

    void calculateTotalPrice(OrderRequest orderRequest, Order order) {

        order.getOrderItemList().clear();




        BigDecimal totalPrice = BigDecimal.valueOf(0);
        for (OrderItemRequest orderItemRequest :  orderRequest.orderItemList()) {

            Product product = productRepository.findById(orderItemRequest.productId())
                    .orElseThrow(() -> new NotFoundException("khong tim thay product voi id: " + orderItemRequest.productId()));

            OrderItem orderItem = orderItemMapper.toEntity(orderItemRequest);

            orderItem.setProduct(product);
            orderItem.setQuantity(orderItemRequest.quantity());
            orderItem.setOrder(order);
            orderItem.setPriceAtPurchase(product.getSellPrice());

            order.getOrderItemList().add(orderItem);


            totalPrice = totalPrice.add(orderItem.getPriceAtPurchase().multiply(BigDecimal.valueOf( orderItem.getQuantity())));
        }
        order.setTotalPrice(totalPrice);
    }


    void updateStatus(Order order) {
        order.setStatus("PENDING");
    }
}
