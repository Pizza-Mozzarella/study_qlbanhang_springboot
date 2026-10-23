package com.springmasterclass.study.service.impl;

import com.springmasterclass.study.dto.request.UserSearchCriteria;
import com.springmasterclass.study.dto.response.PageResponse;
import com.springmasterclass.study.exception.exception.NotFoundException;
import com.springmasterclass.study.mapper.UserMapper;
import com.springmasterclass.study.model.User;
import com.springmasterclass.study.repository.UserRepository;
import com.springmasterclass.study.dto.request.UserRequest;
import com.springmasterclass.study.dto.response.UserResponse;
import com.springmasterclass.study.repository.spec.UserSpec;
import com.springmasterclass.study.service.BaseCrudService;
import com.springmasterclass.study.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanRegistry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl extends BaseCrudService<User, String, UserRequest, UserResponse> implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    //---

    @Override
    protected JpaRepository<User, String> getRepository() {
        return userRepository;
    }

    @Override
    protected User toEntity(UserRequest request) {
        return userMapper.toEntity(request);
    }

    @Override
    protected void updateFromRequest(UserRequest request, User entity) {
        userMapper.updateUserFromRequest(request, entity);
    }

    @Override
    protected UserResponse toResponse(User entity) {
        return userMapper.toResponse(entity);
    }

    @Override
    protected String getEntityName() {
        return "User";
    }

    //---

    @Override
    public UserResponse getUserById(String id) {
        return getById(id);
    }

    @Override
    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        return create(userRequest);
    }

    @Override
    @Transactional
    public UserResponse updateUserById(String id, UserRequest userRequest) {
        return updateById(id, userRequest);
    }

    @Override
    @Transactional
    public void deleteUserById(String id) {
        deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAllUsers() {
        deleteAll();
    }

    //--

    @Override
    public PageResponse<UserResponse> searchUsers(UserSearchCriteria userSearchCriteria, Pageable pageable) {
        Specification<User> userSpecification = UserSpec.nameContains(userSearchCriteria.name());
        Page<User> userPage = userRepository.findAll(userSpecification ,pageable);
        return PageResponse.from(userPage.map(userMapper::toResponse));
    }


}
