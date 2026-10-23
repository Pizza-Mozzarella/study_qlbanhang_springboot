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
import com.springmasterclass.study.service.BaseCrudService;
import com.springmasterclass.study.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl
        extends BaseCrudService<Product, Long, ProductRequest, ProductResponse>
        implements ProductService {

    // ---
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    // ---
    @Override
    protected JpaRepository<Product, Long> getRepository() {
        return productRepository;
    }

    @Override
    protected Product toEntity(ProductRequest request) {
        return productMapper.toEntity(request);
    }

    @Override
    protected void updateFromRequest(ProductRequest request, Product entity) {
        productMapper.updateProductFromRequest(request, entity);
    }

    @Override
    protected ProductResponse toResponse(Product entity) {
        return productMapper.toResponse(entity);
    }

    @Override
    protected String getEntityName() {
        return "Product";
    }

    //---

    @Override
    protected void enrich(ProductRequest request, Product product) {
        product.setCategory(getCategoryOrThrow(request.categoryId()));
    }

    //---

    @Override
    public ProductResponse getProductById(Long id) {
        return getById(id);
    }

    @Override
    @Transactional
    public ProductResponse createProduct(ProductRequest request) {
        return create(request);
    }

    @Override
    @Transactional
    public ProductResponse updateProductById(Long id, ProductRequest request) {
        return updateById(id, request);
    }

    @Override
    @Transactional
    public void deleteProductById(Long id) {
        deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAllProducts() {
        deleteAll();
    }

    // ---
    @Override
    public PageResponse<ProductResponse> searchProducts(ProductSearchCriteria criteria, Pageable pageable) {
        Specification<Product> productSpecification = Specification.where(ProductSpec.inStock())
                .and(ProductSpec.nameContains(criteria.name()))
                .and(ProductSpec.hasCategory(criteria.categoryId()))
                .and(ProductSpec.sellPriceBetween(criteria.minPrice(), criteria.maxPrice()))
                .and(ProductSpec.createdWithinDays(criteria.createdWithinDays()));
        Page<Product> pageProduct = productRepository.findAll(productSpecification, pageable);
        return PageResponse.from(pageProduct.map(productMapper::toResponse));
    }

    // helper riêng của service này
    private Category getCategoryOrThrow(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + categoryId));
    }
}
