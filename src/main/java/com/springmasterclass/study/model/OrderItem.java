package com.springmasterclass.study.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Tạm gỡ @Entity: để dành triển khai ở chương Order (quan hệ qua OrderItem).
// Giữ @Entity khi chưa có @Id sẽ khiến Hibernate không khởi động được app.
@NoArgsConstructor
@Getter
@Setter
public class OrderItem {
}
