package com.springmasterclass.study.service.impl;

import com.springmasterclass.study.dto.request.ProductRequest;
import com.springmasterclass.study.dto.request.ProductSearchCriteria;
import com.springmasterclass.study.dto.response.PageResponse;
import com.springmasterclass.study.dto.response.ProductResponse;
import com.springmasterclass.study.exception.exception.NotFoundException;
import com.springmasterclass.study.mapper.ProductMapper;
import com.springmasterclass.study.model.Category;
import com.springmasterclass.study.model.Product;
import com.springmasterclass.study.repository.CategoryRepository;
import com.springmasterclass.study.repository.ProductRepository;
import com.springmasterclass.study.repository.spec.ProductSpec;
import com.springmasterclass.study.service.ProductService;
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
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public PageResponse<ProductResponse> searchProducts(ProductSearchCriteria criteria, Pageable pageable) {
        Specification<Product> spec = Specification.where(ProductSpec.inStock())
                .and(ProductSpec.nameContains(criteria.name()))
                .and(ProductSpec.hasCategory(criteria.categoryId()))
                .and(ProductSpec.sellPriceBetween(criteria.minPrice(), criteria.maxPrice()))
                .and(ProductSpec.createdWithinDays(criteria.days()));

        Page<Product> productPage = productRepository.findAll(spec, pageable);
        return PageResponse.from(productPage.map(productMapper::toResponse));
    }

    @Override
    public ProductResponse getProductById(Long id) {
        return productMapper.toResponse(productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm với id: " + id)));
    }

    @Override
    @Transactional
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productMapper.toEntity(productRequest);
        product.setCategory(getCategoryOrThrow(productRequest.categoryId()));
        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    @Transactional
    public ProductResponse updateProductById(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm với id: " + id));

        productMapper.updateProductFromRequest(productRequest, product);
        product.setCategory(getCategoryOrThrow(productRequest.categoryId()));
        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    @Transactional
    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException("Không tìm thấy sản phẩm với id: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    private Category getCategoryOrThrow(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy danh mục với id: " + categoryId));
    }
}
