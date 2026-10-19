package com.springmasterclass.study.service;

import com.springmasterclass.study.dto.request.ProductRequest;
import com.springmasterclass.study.dto.request.ProductSearchCriteria;
import com.springmasterclass.study.dto.response.PageResponse;
import com.springmasterclass.study.dto.response.ProductResponse;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    PageResponse<ProductResponse> searchProducts(ProductSearchCriteria criteria, Pageable pageable);

    ProductResponse getProductById(Long id);

    ProductResponse createProduct(ProductRequest productRequest);

    ProductResponse updateProductById(Long id, ProductRequest productRequest);

    void deleteProductById(Long id);

    void deleteAllProducts();
}
