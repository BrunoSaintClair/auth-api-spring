package com.example.Auth_Api.dto;

public record LoginResponseDto(String token, int expirationTimeInSeconds, String tokenType) {
}
