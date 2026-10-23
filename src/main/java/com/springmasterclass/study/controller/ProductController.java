package com.springmasterclass.study.controller;

import com.springmasterclass.study.common.ApiResponse;
import com.springmasterclass.study.common.BaseController;
import com.springmasterclass.study.dto.request.ProductRequest;
import com.springmasterclass.study.dto.request.ProductSearchCriteria;
import com.springmasterclass.study.dto.response.PageResponse;
import com.springmasterclass.study.dto.response.ProductResponse;
import com.springmasterclass.study.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController extends BaseController {

    private final ProductService productService;

    @GetMapping
    public ApiResponse<PageResponse<ProductResponse>> index(
            @ModelAttribute ProductSearchCriteria criteria,
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return createSuccessResponse(productService.searchProducts(criteria, pageable));
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> show(@PathVariable Long id) {
        return createSuccessResponse(productService.getProductById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ProductResponse> create(@Valid @RequestBody ProductRequest productRequest) {
        return createSuccessResponse(productService.createProduct(productRequest));
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductResponse> update(@PathVariable Long id, @Valid @RequestBody ProductRequest productRequest) {
        return createSuccessResponse(productService.updateProductById(id, productRequest));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        productService.deleteProductById(id);
        return createSuccessResponse("Đã xóa thành công sản phẩm có id: " + id);
    }

    @DeleteMapping
    public ApiResponse<String> deleteAll() {
        productService.deleteAllProducts();
        return createSuccessResponse("Đã xóa thành công tất cả sản phẩm");
    }
}
