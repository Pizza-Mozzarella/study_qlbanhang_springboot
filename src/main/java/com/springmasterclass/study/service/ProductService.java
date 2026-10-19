package com.springmasterclass.study.service;

import com.springmasterclass.study.dto.request.ProductRequest;
import com.springmasterclass.study.dto.request.ProductSearchCriteria;
import com.springmasterclass.study.dto.request.UserRequest;
import com.springmasterclass.study.dto.response.PageResponse;
import com.springmasterclass.study.dto.response.ProductResponse;
import com.springmasterclass.study.dto.response.UserResponse;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    // PageResponse<ProductResponse> getAllProducts(Pageable pageable);

    PageResponse<ProductResponse> searchProducts(ProductSearchCriteria criteria, Pageable pageable);

    ProductResponse getProductById(String id);

    ProductResponse createProduct(ProductRequest ProductRequest);

    ProductResponse updateProductById(String id, ProductRequest ProductRequest);

    void deleteProductById(String id);

    void deleteALLProduct();
}
