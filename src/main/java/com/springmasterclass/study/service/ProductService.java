package com.springmasterclass.study.service;

import com.springmasterclass.study.dto.response.PageResponse;
import com.springmasterclass.study.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    PageResponse<ProductResponse> getAllProducts(Pageable pageable);

}
