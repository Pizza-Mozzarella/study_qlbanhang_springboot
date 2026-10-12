package com.springmasterclass.study.exception;


import com.springmasterclass.study.exception.exception.BadRequestException;
import com.springmasterclass.study.exception.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Map;


@ControllerAdvice(basePackages = "com.springmasterclass.study")
public class GlobalExceptionHandler {


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "status", 404,
                "message", ex.getMessage(),
                "timestamp", LocalDateTime.now()
        ));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequest(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "status", 400,
                "message", ex.getMessage(),
                "timestamp", LocalDateTime.now()
        ));
    }


    // MethodArgumentNotValidException là exception của validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        // Gom tất cả các trường bị lỗi thành Map<tên_field, câu_chửi>
        Map<String, String> errors = new java.util.HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "status", 400,
                "error", "Validation Error",
                "errors", errors, // Trả danh sách chi tiết từng trường
                "timestamp", LocalDateTime.now()
        ));
    }

    /* chỉ hiện 1 lỗi 1 lần
     @ExceptionHandler(MethodArgumentNotValidException.class) // Hoặc BindException
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        // 1. Lấy thông báo lỗi đầu tiên (hoặc gom hết lỗi nếu muốn)
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        // 2. Trả về JSON đàng hoàng với ResponseEntity
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "status", 400,
                "error", "Validation Error",
                "message", message,
                "timestamp", LocalDateTime.now()
        ));
    }
     */


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "status", 500,
                "message", "Đã xảy ra lỗi hệ thống. Vui lòng thử lại sau!",  // ← CHE GIẤU LỖI!
                "timestamp", LocalDateTime.now()
        ));
    }

}