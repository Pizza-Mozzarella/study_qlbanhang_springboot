package com.springmasterclass.study.service.impl;

import com.springmasterclass.study.dto.request.ProductRequest;
import com.springmasterclass.study.dto.request.ProductSearchCriteria;
import com.springmasterclass.study.dto.response.PageResponse;
import com.springmasterclass.study.mapper.ProductMapper;
import com.springmasterclass.study.model.Product;
import com.springmasterclass.study.repository.ProductRepository;
import com.springmasterclass.study.repository.spec.ProductSpec;
import com.springmasterclass.study.service.ProductService;
import com.springmasterclass.study.dto.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public PageResponse<ProductResponse> searchProducts(ProductSearchCriteria criteria, Pageable pageable) {
        Specification<Product> spec = Specification.where(ProductSpec.inStock())
                .and(ProductSpec.nameContains(criteria.name()))
                .and(ProductSpec.hasCategory(criteria.categoryId()))
                .and(ProductSpec.sellPriceBetween(criteria.minPrice(), criteria.maxPrice()));

        Page<Product> productPage = productRepository.findAll(spec, pageable);
        return PageResponse.from(productPage.map(productMapper::toResponse));
    }

    @Override
    public ProductResponse getProductById(String id) {
        return null;
    }

    @Override
    public ProductResponse createProduct(ProductRequest ProductRequest) {
        return null;
    }

    @Override
    public ProductResponse updateProductById(String id, ProductRequest ProductRequest) {
        return null;
    }

    @Override
    public void deleteProductById(String id) {

    }

    @Override
    public void deleteALLProduct() {

    }
}
