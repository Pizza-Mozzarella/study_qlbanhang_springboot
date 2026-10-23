package com.springmasterclass.study.mapper;

import com.springmasterclass.study.dto.request.OrderRequest;
import com.springmasterclass.study.dto.response.OrderResponse;
import com.springmasterclass.study.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    OrderResponse toResponse(Order order);


    @Mapping(target = "id", ignore = true)
    Order toEntity(OrderRequest orderRequest);

}
