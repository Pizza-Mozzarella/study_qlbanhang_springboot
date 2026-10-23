package com.springmasterclass.study.mapper;

import com.springmasterclass.study.dto.request.CategoryRequest;
import com.springmasterclass.study.dto.response.CategoryResponse;
import com.springmasterclass.study.model.Category;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

    CategoryResponse toResponse(Category category);

    @Mapping(target = "id", ignore = true)
    Category toEntity(CategoryRequest categoryRequest);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategoryFromRequest(CategoryRequest categoryRequest, @MappingTarget Category category);
}
