package com.springmasterclass.study.service;

import com.springmasterclass.study.dto.request.CategoryRequest;
import com.springmasterclass.study.dto.request.CategorySearchCriteria;
import com.springmasterclass.study.dto.response.CategoryResponse;
import com.springmasterclass.study.dto.response.PageResponse;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    PageResponse<CategoryResponse> searchCategories(CategorySearchCriteria categorySearchCriteria, Pageable pageable);

    CategoryResponse getCategoryById(Long id);

    CategoryResponse createCategory(CategoryRequest categoryRequest);

    CategoryResponse updateCategoryById(Long id, CategoryRequest categoryRequest);

    void deleteCategoryById(Long id);

    void deleteAllCategories();
}
