package com.springmasterclass.study.mapper;

import com.springmasterclass.study.dto.request.OrderItemRequest;
import com.springmasterclass.study.dto.response.OrderItemResponse;
import com.springmasterclass.study.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper {


    OrderItemResponse toResponse(OrderItem orderItem);

    @Mapping(target = "id", ignore = true)
    OrderItem toEntity(OrderItemRequest orderItemRequest);

}
