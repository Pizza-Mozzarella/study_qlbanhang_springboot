package com.springmasterclass.study.service.impl;

import com.springmasterclass.study.dto.response.PageResponse;
import com.springmasterclass.study.exception.exception.NotFoundException;
import com.springmasterclass.study.mapper.UserMapper;
import com.springmasterclass.study.model.User;
import com.springmasterclass.study.repository.UserRepository;
import com.springmasterclass.study.dto.request.UserRequest;
import com.springmasterclass.study.dto.response.UserResponse;
import com.springmasterclass.study.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public PageResponse<UserResponse> getAllUser(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return PageResponse.from(userPage.map(userMapper::toResponse));
    }

    @Override
    public UserResponse getUserById(String id) {
        return userMapper.toResponse(userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("khong tim thay user co id: " + id)));
    }

    @Override
    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        return userMapper.toResponse(userRepository.save(userMapper.toEntity(userRequest)));
    }

    @Override
    @Transactional
    public UserResponse updateUserById(String id, UserRequest userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("khong tim thay user co id: " + id));

        userMapper.updateUserFromRequest(userRequest, user);
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    @Transactional
    public void deleteUserById(String id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("Không tìm thấy người dùng với id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteALLUser() {
        userRepository.deleteAll();
    }
}
