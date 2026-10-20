package com.springmasterclass.study.repository.spec;

import com.springmasterclass.study.model.Product;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public final class ProductSpec {

    private ProductSpec() {}


    public static Specification<Product> nameContains(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return Specification.unrestricted();
        }
        String pattern = "%"  + keyword.trim().toLowerCase() + "%";
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), pattern);


    }
    /** Lọc theo khoảng giá bán [min, max] */
    public static Specification<Product> sellPriceBetween(Double min, Double max) {
        if (min == null && max == null) {
            return Specification.unrestricted();
        }
        return (root, query, cb) -> {
            if (min != null && max != null) {
                return cb.between(root.get("sellPrice"), min, max);
            }
            if (min != null) {
                return cb.greaterThanOrEqualTo(root.get("sellPrice"), min);
            }
            return cb.lessThanOrEqualTo(root.get("sellPrice"), max);
        };
    }

    /** Chỉ lấy sản phẩm còn hàng */
    public static Specification<Product> inStock() {
        return (root, query, cb) -> cb.greaterThan(root.get("stock"), 0);
    }

    /** Lọc theo danh mục */
    public static Specification<Product> hasCategory(Long categoryId) {
        if (categoryId == null) {
            return Specification.unrestricted();
        }
        return (root, query, cb) -> cb.equal(root.get("category").get("id"), categoryId);
    }

    public static Specification<Product> createdWithinDays(Integer days) {
        if (days == null) {
            return Specification.unrestricted();
        }
        return (root, query, cb) -> {
             java.time.LocalDateTime daysWithIn = LocalDateTime.now().minusDays(days);

             return cb.greaterThanOrEqualTo(root.get("createdAt"), daysWithIn);

        };

    }

}
