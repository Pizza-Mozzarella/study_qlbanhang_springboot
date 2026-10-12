package com.springmasterclass.study.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {


    protected <T>  ApiResponse<T> createSuccessResponse(T data) {
        return ApiResponse.success(data);
    }

}
