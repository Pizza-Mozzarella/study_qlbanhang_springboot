package com.springmasterclass.study.dto.response;


import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Builder
public class PageResponse<T> {
    private List<T> items;        // Danh sách dữ liệu của trang đó
    private int pageNo;           // Trang hiện tại
    private int pageSize;         // Số phần tử trên 1 trang
    private long totalElements;   // Tổng số bản ghi (VD: 150)
    private int totalPages;       // Tổng số trang (VD: 15)
    private boolean isLast;       // Đã là trang cuối chưa?

    public static <T> PageResponse<T> from(Page<T> page) {
        return PageResponse.<T>builder()
                .items(page.getContent())
                .pageNo(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .isLast(page.isLast())
                .build();
    }
}