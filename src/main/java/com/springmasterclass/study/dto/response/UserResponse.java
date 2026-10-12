package com.springmasterclass.study.dto.response;

public record UserResponse(
        String id,
        String username,
        String email,
        String name,
        String phone,
        String address
) { }


