package com.springmasterclass.study.model;

import jakarta.persistence.*;
import lombok.*;

// Tạm gỡ @Entity: để dành triển khai ở chương Order (quan hệ qua OrderItem).
// Giữ @Entity khi chưa có @Id sẽ khiến Hibernate không khởi động được app.
@NoArgsConstructor
@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "Order_Items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private int quantity;
    private double price;


}
