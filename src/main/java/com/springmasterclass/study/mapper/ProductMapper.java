package com.springmasterclass.study.mapper;

import com.springmasterclass.study.model.Product;
import com.springmasterclass.study.dto.request.ProductRequest;
import com.springmasterclass.study.dto.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    @Mapping(target = "createdAt", ignore = true)
    ProductResponse toResponse(Product product);

    // Tạo entity mới cho luồng create.
    // category: service tra Category rồi set tay. createdAt: @PrePersist tự điền lúc insert.
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Product toEntity(ProductRequest productRequest);


    // Cập nhật lên entity đã load từ DB (@MappingTarget), không tạo mới.
    // createdAt phải ignore: update không bao giờ được ghi đè ngày tạo.
    // category: service set tay sau khi map.
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateProductFromRequest(ProductRequest productRequest, @MappingTarget Product product);
}
