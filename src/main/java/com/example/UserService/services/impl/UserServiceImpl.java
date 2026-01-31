package com.example.UserService.services.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.UserService.dtos.ApiResponseDTO;
import com.example.UserService.dtos.UserRequestDto;
import com.example.UserService.models.UsersEntity;
import com.example.UserService.repositories.UserRepo;
import com.example.UserService.services.UserService;
import com.example.UserService.utils.JwtUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final JwtUtils jwtUtils;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ApiResponseDTO<?> register(UserRequestDto request) {

        UsersEntity existingUser = userRepo.findByEmail(request.getEmail());

        if (existingUser != null) {
            return ApiResponseDTO.builder()
                    .success(false)
                    .message("User already exists")
                    .build();
        }

        UsersEntity user = UsersEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepo.save(user);

        return ApiResponseDTO.builder()
                .success(true)
                .message("Registered successfully")
                .build();
    }

    @Override
    public ApiResponseDTO<?> login(UserRequestDto request) {

        UsersEntity user = userRepo.findByEmail(request.getEmail());

        if (user == null) {
            return ApiResponseDTO.builder()
                    .success(false)
                    .message("Invalid email or password")
                    .build();
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ApiResponseDTO.builder()
                    .success(false)
                    .message("Invalid email or password")
                    .build();
        }

        String token = jwtUtils.generateToken(
                user.getEmail(),
                String.valueOf(user.getId())
        );

        return ApiResponseDTO.builder()
                .success(true)
                .message("Login successful")
                .data(token)
                .build();
    }
}
