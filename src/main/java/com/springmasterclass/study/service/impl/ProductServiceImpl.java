package com.springmasterclass.study.service.impl;

import com.springmasterclass.study.dto.response.PageResponse;
import com.springmasterclass.study.mapper.ProductMapper;
import com.springmasterclass.study.model.Product;
import com.springmasterclass.study.repository.ProductRepository;
import com.springmasterclass.study.service.ProductService;
import com.springmasterclass.study.dto.response.ProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public PageResponse<ProductResponse> getAllProducts(Pageable pageable) {

        Page<Product> productPage = productRepository.findAll(pageable);

        Page<ProductResponse> dtoPage = productPage.map(productMapper::toResponse);

        return PageResponse.from(dtoPage);

    }
}
