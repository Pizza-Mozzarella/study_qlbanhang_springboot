package com.springmasterclass.study.service.impl;

import com.springmasterclass.study.dto.request.CategoryRequest;
import com.springmasterclass.study.dto.request.CategorySearchCriteria;
import com.springmasterclass.study.dto.response.CategoryResponse;
import com.springmasterclass.study.dto.response.PageResponse;
import com.springmasterclass.study.exception.exception.NotFoundException;
import com.springmasterclass.study.mapper.CategoryMapper;
import com.springmasterclass.study.model.Category;
import com.springmasterclass.study.repository.CategoryRepository;
import com.springmasterclass.study.repository.spec.CategorySpec;
import com.springmasterclass.study.service.BaseCrudService;
import com.springmasterclass.study.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl extends BaseCrudService<Category, Long, CategoryRequest, CategoryResponse>
        implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    // 5 Base
    @Override
    protected JpaRepository<Category, Long> getRepository() {
        return categoryRepository;
    }

    @Override
    protected Category toEntity(CategoryRequest request) {
        return categoryMapper.toEntity(request);
    }

    @Override
    protected void updateFromRequest(CategoryRequest request, Category entity) {
        categoryMapper.updateCategoryFromRequest(request, entity);
    }

    @Override
    protected CategoryResponse toResponse(Category entity) {
        return categoryMapper.toResponse(entity);
    }

    @Override
    protected String getEntityName() {
        return "Category";
    }

    //--

    @Override
    public CategoryResponse getCategoryById(Long id) {
       return getById(id);
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        return create(categoryRequest);
    }

    @Override
    public CategoryResponse updateCategoryById(Long id, CategoryRequest categoryRequest) {
        return updateById(id, categoryRequest);
    }

    @Override
    public void deleteCategoryById(Long id) {
        deleteById(id);
    }

    @Override
    public void deleteAllCategories() {
        categoryRepository.deleteAll();
    }

    // --

    @Override
    public PageResponse<CategoryResponse> searchCategories(CategorySearchCriteria categorySearchCriteria, Pageable pageable) {
        Specification<Category> categorySpecification = Specification.where(CategorySpec.nameContains(categorySearchCriteria.name()));
        Page<Category> categoryPage = categoryRepository.findAll(categorySpecification, pageable);
        return PageResponse.from(categoryPage.map(categoryMapper::toResponse));
    }





}
