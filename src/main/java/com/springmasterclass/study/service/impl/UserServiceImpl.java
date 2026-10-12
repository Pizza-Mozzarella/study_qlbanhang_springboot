package com.springmasterclass.study.service.impl;

import com.springmasterclass.study.dto.response.PageResponse;
import com.springmasterclass.study.exception.exception.NotFoundException;
import com.springmasterclass.study.mapper.UserMapper;
import com.springmasterclass.study.model.User;
import com.springmasterclass.study.repository.UserRepository;
import com.springmasterclass.study.dto.request.UserRequest;
import com.springmasterclass.study.dto.response.UserResponse;
import com.springmasterclass.study.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public PageResponse<UserResponse> getAllUser(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        Page<UserResponse> userResponsePage = userPage.map(userMapper::toResponse);
        return PageResponse.from(userResponsePage);
        // return users.map(userMapper::toResponse);


    }

    @Override
    public UserResponse getUserById(String id) {
        return userMapper.toResponse(userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("khong tim thay user co id: " + id)));
    }


    @Override
    public UserResponse createUser(UserRequest userRequest) {
        return userMapper.toResponse(userRepository.save(userMapper.toEntity(userRequest)));
    }

    @Override
    public UserResponse updateUserById(String id, UserRequest userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("khong tim thay user co id: " + id));

        user.setUsername(userRequest.username());
        user.setPassword(userRequest.password());
        user.setEmail(userRequest.email());
        user.setName(userRequest.name());
        user.setPhone(userRequest.phone());
        user.setAddress(userRequest.address());

        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public void deleteUserById(String id) {
        // không cần findById, vì không cần trả về User
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("Không tìm thấy người dùng với id: " + id);
        }
        userRepository.deleteById(id);

    }

    @Override
    public void deleteALLUser() {
        userRepository.deleteAll();
    }
}
