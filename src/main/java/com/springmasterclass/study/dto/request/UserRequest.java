package com.springmasterclass.study.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record UserRequest(

        @NotBlank(message = "ten nguoi dung khong duoc de trong")
        @Size(min = 6, max = 20, message = "ten nguoi dung phai tu 6 den 20 ky tu")
        String username,

        @NotBlank(message = "mat khau khong duoc de trong")
        @Size(min = 8, max = 100, message = "mat khau phai tu 8 den 100 ky tu")
        String password,

        @NotBlank(message = "email khong duoc de trong")
        @Email(message = "email khong hop le")
        String email,

        @NotBlank(message = "ho ten nguoi dung khong duoc de trong")
        String name,

        @NotBlank(message = "so dien thoai khong duoc de trong")
        @Size(min = 9, max = 11, message = "so dien thoai phai tu 9 den 11 ky tu")
        String phone,

        String address

) {}
