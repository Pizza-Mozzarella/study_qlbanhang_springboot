package com.springmasterclass.study.controller;

import com.springmasterclass.study.common.ApiResponse;
import com.springmasterclass.study.common.BaseController;
import com.springmasterclass.study.dto.request.CategoryRequest;
import com.springmasterclass.study.dto.request.CategorySearchCriteria;
import com.springmasterclass.study.dto.response.CategoryResponse;
import com.springmasterclass.study.dto.response.PageResponse;
import com.springmasterclass.study.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController extends BaseController {

    private final CategoryService categoryService;

    @GetMapping
    public ApiResponse<PageResponse<CategoryResponse>> index(
            @ModelAttribute CategorySearchCriteria criteria,
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable

    ) {
        return createSuccessResponse(categoryService.searchCategories(criteria, pageable));
    }

    @GetMapping("/{id}")
    public ApiResponse<CategoryResponse> show(@PathVariable Long id) {
        return createSuccessResponse(categoryService.getCategoryById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CategoryResponse> create(@Valid @RequestBody CategoryRequest categoryRequest) {
        return createSuccessResponse(categoryService.createCategory(categoryRequest));
    }

    @PutMapping("/{id}")
    public ApiResponse<CategoryResponse> update(@PathVariable Long id, @Valid @RequestBody CategoryRequest categoryRequest) {
        return createSuccessResponse(categoryService.updateCategoryById(id, categoryRequest));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return createSuccessResponse("Đã xóa thành công danh mục có id: " + id);
    }

    @DeleteMapping
    public ApiResponse<String> deleteAll() {
        categoryService.deleteAllCategories();
        return createSuccessResponse("Đã xóa thành công tất cả danh mục");
    }
}
