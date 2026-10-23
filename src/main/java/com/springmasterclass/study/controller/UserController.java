package com.springmasterclass.study.controller;

import com.springmasterclass.study.common.ApiResponse;
import com.springmasterclass.study.common.BaseController;
import com.springmasterclass.study.dto.request.UserSearchCriteria;
import com.springmasterclass.study.dto.response.PageResponse;
import com.springmasterclass.study.service.UserService;
import com.springmasterclass.study.dto.request.UserRequest;
import com.springmasterclass.study.dto.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "api/v1/users")
@RequiredArgsConstructor
public class UserController extends BaseController {

    private final UserService userService;

    @GetMapping
    public ApiResponse<PageResponse<UserResponse>> index(
            @ModelAttribute UserSearchCriteria userSearchCriteria,
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return createSuccessResponse(userService.searchUsers(userSearchCriteria ,pageable));
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> show(@PathVariable String id) {
        return createSuccessResponse(userService.getUserById(id));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserResponse> create(@Valid @RequestBody UserRequest userRequest) {
        return createSuccessResponse(userService.createUser(userRequest));
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> update(@PathVariable String id, @Valid @RequestBody UserRequest userRequest) {
        return createSuccessResponse(userService.updateUserById(id, userRequest));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable String id) {
        userService.deleteUserById(id);
        return createSuccessResponse("da delete thanh cong user co id: " + id);
    }

    @DeleteMapping
    public ApiResponse<String> deleteAll() {
        userService.deleteAllUsers();
        return createSuccessResponse("da delete thanh cong tat ca user");
    }


}
