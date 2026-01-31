package com.example.UserService.controllers;

import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*;

import com.example.UserService.dtos.ApiResponseDTO;
import com.example.UserService.dtos.UserRequestDto;
import com.example.UserService.services.UserService; 

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
 

    // Register endpoint
    @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO<?>> register(@Valid @RequestBody UserRequestDto request) {
        ApiResponseDTO<?> response = userService.register(request);
        return ResponseEntity.ok(response);
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO<?>> login(@Valid @RequestBody UserRequestDto request) {
        ApiResponseDTO<?> response = userService.login(request);
        return ResponseEntity.ok(response);
    }

   
}
