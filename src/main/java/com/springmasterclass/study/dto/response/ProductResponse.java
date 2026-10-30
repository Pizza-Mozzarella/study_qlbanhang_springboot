package com.springmasterclass.study.dto.response;

import java.math.BigDecimal;

public record ProductResponse (
        Long id,
        String name,
        BigDecimal sellPrice,
        CategoryResponse category,
        int stock
) {
}
