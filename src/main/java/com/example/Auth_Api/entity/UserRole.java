package com.example.Auth_Api.entity;

import lombok.Getter;

@Getter
public enum UserRole {
    USER("user"),
    ADMIN("admin");

    private String role;

    UserRole(String role){
        this.role = role;
    }
}
