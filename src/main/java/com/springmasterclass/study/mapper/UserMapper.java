package com.springmasterclass.study.mapper;

import com.springmasterclass.study.dto.request.UserRequest;
import com.springmasterclass.study.dto.response.UserResponse;
import com.springmasterclass.study.model.User;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserResponse toResponse(User user);

    @Mapping(target = "id", ignore = true)
    User toEntity(UserRequest request);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromRequest(UserRequest request, @MappingTarget User user);
}
