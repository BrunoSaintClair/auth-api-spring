package com.example.Auth_Api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private HttpStatus status;
    private String message;

}
