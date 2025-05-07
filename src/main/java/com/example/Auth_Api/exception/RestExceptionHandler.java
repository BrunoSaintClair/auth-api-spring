package com.example.Auth_Api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotUniqueEmailException.class)    
    private ResponseEntity<ExceptionResponse> notUniqueEmailHandler(NotUniqueEmailException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(MissingFieldException.class)
    private ResponseEntity<ExceptionResponse> missingFieldHandler(MissingFieldException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(PasswordOrEmailInvalidException.class)
    private ResponseEntity<ExceptionResponse> passwordOrEmailHandler(PasswordOrEmailInvalidException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

}
