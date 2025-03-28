package com.example.Auth_Api.dto;

import com.example.Auth_Api.entity.UserRole;

public record RegisterDto(
        String email, String password, UserRole role
) {
}
