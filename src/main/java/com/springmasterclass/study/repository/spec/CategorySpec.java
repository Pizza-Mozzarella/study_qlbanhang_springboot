package com.springmasterclass.study.repository.spec;

import com.springmasterclass.study.model.Category;
import org.springframework.data.jpa.domain.Specification;

public final class CategorySpec {
    private CategorySpec() {}

    public static Specification<Category> nameContains(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return Specification.unrestricted();
        }
        String pattern = "%"  + keyword.trim().toLowerCase() + "%";
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), pattern);
    }



}
