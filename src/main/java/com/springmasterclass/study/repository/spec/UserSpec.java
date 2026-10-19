package com.springmasterclass.study.repository.spec;

import com.springmasterclass.study.model.Product;
import org.springframework.data.jpa.domain.Specification;

public final class UserSpec {
    public static Specification<Product> nameContains(String keyword) {
        return null;
    }


}
