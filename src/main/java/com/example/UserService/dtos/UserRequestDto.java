package com.example.UserService.dtos;

import lombok.Builder;
import lombok.Data;

/**
 * Generic DTO for taking user input
 * Can be used for Registration or Login
 */
@Data
@Builder
public class UserRequestDto {

    private Long id;

    private String email;

    private String password;

    private String name;

    private String role;
}
