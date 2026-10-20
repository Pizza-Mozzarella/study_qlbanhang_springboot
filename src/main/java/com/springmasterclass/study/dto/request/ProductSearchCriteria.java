package com.springmasterclass.study.dto.request;

public record ProductSearchCriteria(
        String name,
        Double minPrice,
        Double maxPrice,
        Long categoryId,
        Integer createdWithinDays
) {
}
