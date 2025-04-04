package com.example.Auth_Api.controller;

import com.example.Auth_Api.dto.LoginDto;
import com.example.Auth_Api.dto.RegisterDto;
import com.example.Auth_Api.entity.User;
import com.example.Auth_Api.dto.LoginResponseDto;
import com.example.Auth_Api.dto.RegisterResponseDto;
import com.example.Auth_Api.service.AuthService;
import com.example.Auth_Api.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto data){
        var emailAndPassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(emailAndPassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        LoginResponseDto loginResponse = new LoginResponseDto(token, 7200,"Bearer");
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterDto data){
        var user = authService.register(data);
        RegisterResponseDto userResponse = new RegisterResponseDto(user.getId(), "User created successfully");
        return ResponseEntity.ok(userResponse);
    }
}
