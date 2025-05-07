package com.example.Auth_Api.exception;

public class PasswordOrEmailInvalidException extends RuntimeException {
    public PasswordOrEmailInvalidException(String message) { super(message); }
}
