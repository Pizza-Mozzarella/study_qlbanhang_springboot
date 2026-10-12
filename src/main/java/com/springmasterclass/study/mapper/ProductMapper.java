package com.springmasterclass.study.mapper;

import com.springmasterclass.study.model.Product;
import com.springmasterclass.study.dto.request.ProductRequest;
import com.springmasterclass.study.dto.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {


    ProductResponse toResponse(Product product);

    @Mapping(target = "id", ignore = true)
    Product toEntity(ProductRequest productRequest);


}
