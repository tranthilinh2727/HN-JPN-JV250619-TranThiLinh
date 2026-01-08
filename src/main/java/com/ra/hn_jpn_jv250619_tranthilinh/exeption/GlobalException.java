package com.ra.hn_jpn_jv250619_tranthilinh.exeption;

import com.ra.hn_jpn_jv250619_tranthilinh.model.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<?> handleValidation(MethodArgumentNotValidException e) {
        List<String> errorMessages = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        return new ApiResponse<>(
                false,
                "Validation error",
                null,
                errorMessages,
                HttpStatus.BAD_REQUEST.value()
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ApiResponse<?> handleRuntime(RuntimeException e) {
        return new ApiResponse<>(
                false,
                "Lỗi hệ thống: " + e.getMessage(),
                null,
                null,
                HttpStatus.BAD_REQUEST.value()
        );
    }
}
