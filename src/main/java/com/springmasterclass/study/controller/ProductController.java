package com.springmasterclass.study.controller;


import com.springmasterclass.study.common.ApiResponse;
import com.springmasterclass.study.common.BaseController;
import com.springmasterclass.study.dto.response.PageResponse;
import com.springmasterclass.study.service.ProductService;
import com.springmasterclass.study.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/products")
public class ProductController extends BaseController {

    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping()
    public ApiResponse<PageResponse<ProductResponse>> index(
            @RequestParam(value = "page_no", defaultValue = "0") int page,
            @RequestParam(value = "page_size", defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
           // @RequestParam(value = "name", required = false) String name
    ) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return ApiResponse.success(productService.getAllProducts(pageable));
    }


}
