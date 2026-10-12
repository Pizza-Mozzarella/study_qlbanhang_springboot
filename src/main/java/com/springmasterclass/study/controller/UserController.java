package com.springmasterclass.study.controller;

import com.springmasterclass.study.common.ApiResponse;
import com.springmasterclass.study.common.BaseController;
import com.springmasterclass.study.dto.response.PageResponse;
import com.springmasterclass.study.service.UserService;
import com.springmasterclass.study.dto.request.UserRequest;
import com.springmasterclass.study.dto.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "api/v1/users")
public class UserController extends BaseController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ApiResponse<PageResponse<UserResponse>> index(
            @RequestParam(value = "page_no", defaultValue = "0") int page,
            @RequestParam(value = "page_size", defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ){
        // câu này là sort.by() -> trả về sort mới cập nhật by = sortby sort.descending() trả vè
        //  sort mới cập nhật sortDir = descending
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return ApiResponse.success(userService.getAllUser(pageable));
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> show(@PathVariable String id) {
        return createSuccessResponse(userService.getUserById(id));
    }

    @PostMapping()
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
        userService.deleteALLUser();
        return createSuccessResponse("da delete thanh cong tat ca user");
    }


}
