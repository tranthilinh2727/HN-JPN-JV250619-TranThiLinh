package com.ra.hn_jpn_jv250619_tranthilinh.model.dto;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private Object error;
    private int httpStatus;
}

