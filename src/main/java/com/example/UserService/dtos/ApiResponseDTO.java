package com.example.UserService.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponseDTO<T> {

    private boolean success;
    private String message;
    private T data; // can be user info, JWT, etc.
}