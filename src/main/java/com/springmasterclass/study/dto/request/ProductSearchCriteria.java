package com.springmasterclass.study.dto.request;

import java.math.BigDecimal;

public record ProductSearchCriteria(
        String name,
        BigDecimal minPrice,
        BigDecimal maxPrice,
        Long categoryId,
        Integer createdWithinDays
) {
}
