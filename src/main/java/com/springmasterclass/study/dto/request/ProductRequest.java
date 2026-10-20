package com.springmasterclass.study.dto.request;

import jakarta.validation.constraints.*;

public record   ProductRequest (
        @NotBlank(message = "Tên sản phẩm không được để trống")
        @Size(min = 3, max = 100, message = "Tên sản phẩm phải từ 3 đến 100 ký tự")
        String name,
        @Positive(message = "Giá bán phải lớn hơn 0")
        double sellPrice,
        @PositiveOrZero(message = "Giá vốn không được là số âm")
        double costPrice,
        @NotNull(message = "Category ID không được để trống")
        Long categoryId,
        @Min(value = 0, message = "Số lượng tồn kho không được nhỏ hơn 0")
        int stock,
        @Min(value = 0, message = "Ngày tạo gần đây không được âm")
        Integer days
) { }

