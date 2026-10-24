package com.springmasterclass.study.repository;

import com.springmasterclass.study.model.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    @EntityGraph(attributePaths = {
            "user",
            "orderItemList",
            "orderItemList.product",
            "orderItemList.product.category"
    })
    Optional<Order> findWithDetailsById(Long id);

}
