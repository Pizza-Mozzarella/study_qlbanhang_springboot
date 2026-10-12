package com.springmasterclass.study.service;

import com.springmasterclass.study.dto.request.UserRequest;
import com.springmasterclass.study.dto.response.PageResponse;
import com.springmasterclass.study.dto.response.UserResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {

    public PageResponse<UserResponse> getAllUser(Pageable pageable);

    UserResponse getUserById(String id);

    UserResponse createUser(UserRequest userRequest);

    UserResponse updateUserById(String id, UserRequest userRequest);

    void deleteUserById(String id);

    void deleteALLUser();

}
