package com.springmasterclass.study.dto.response;

public record ProductResponse (
        Long id,
        String name,
        double sellPrice,
        CategoryResponse category,
        int stock
) {
}
