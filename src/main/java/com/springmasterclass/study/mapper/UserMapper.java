package com.springmasterclass.study.mapper;


import com.springmasterclass.study.model.User;
import com.springmasterclass.study.dto.request.UserRequest;
import com.springmasterclass.study.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {


    UserResponse toResponse(User user);

    @Mapping(target = "id", ignore = true)
    User toEntity(UserRequest request);
}
