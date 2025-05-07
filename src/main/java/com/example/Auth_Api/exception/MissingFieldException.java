package com.example.Auth_Api.exception;

public class MissingFieldException extends RuntimeException {
    public MissingFieldException(String field) { super("Field " + field + " can't be blank."); }
}
