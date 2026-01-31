package com.example.UserService.services;

import com.example.UserService.dtos.ApiResponseDTO;
import com.example.UserService.dtos.UserRequestDto;

public interface UserService {
    ApiResponseDTO<?> register(UserRequestDto request);

    ApiResponseDTO<?> login(UserRequestDto request); 
}
