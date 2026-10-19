package com.springmasterclass.study.service.impl;

import com.springmasterclass.study.dto.request.CategoryRequest;
import com.springmasterclass.study.dto.response.CategoryResponse;
import com.springmasterclass.study.dto.response.PageResponse;
import com.springmasterclass.study.exception.exception.NotFoundException;
import com.springmasterclass.study.mapper.CategoryMapper;
import com.springmasterclass.study.model.Category;
import com.springmasterclass.study.repository.CategoryRepository;
import com.springmasterclass.study.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public PageResponse<CategoryResponse> getAllCategories(Pageable pageable) {
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return PageResponse.from(categoryPage.map(categoryMapper::toResponse));
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        return categoryMapper.toResponse(categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy danh mục với id: " + id)));
    }

    @Override
    @Transactional
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        return categoryMapper.toResponse(categoryRepository.save(categoryMapper.toEntity(categoryRequest)));
    }

    @Override
    @Transactional
    public CategoryResponse updateCategoryById(Long id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy danh mục với id: " + id));

        categoryMapper.updateCategoryFromRequest(categoryRequest, category);
        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void deleteCategoryById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException("Không tìm thấy danh mục với id: " + id);
        }
        categoryRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAllCategories() {
        categoryRepository.deleteAll();
    }
}
