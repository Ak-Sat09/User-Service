package com.example.UserService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.UserService.models.UsersEntity;

public interface UserRepo extends JpaRepository<UsersEntity, Long> {
    UsersEntity findByEmail(String email);
 
}
