package com.ra.hn_jpn_jv250619_tranthilinh.exeption;

import com.ra.hn_jpn_jv250619_tranthilinh.model.dto.ApiResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<?>handleValidation(MethodArgumentNotValidException e) {
        return new ApiResponse<>(
                false,
                "Validation eror",
                null,
                e.getBindingResult().getFieldError(),
                400
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ApiResponse<?>handleRuntime(RuntimeException e) {
        return new ApiResponse<>(
                false,
                "Validation eror",
                null,
                null,
                400
        );
    }
}
