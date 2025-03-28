package com.example.Auth_Api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Controller de exemplo apenas para testar permissões.

@RestController
@RequestMapping("/msg")
public class ExampleController {

    @GetMapping
    public String withoutLogin(){
        return "Without login working";
    }

    @GetMapping("/logged-in")
    public String withLogin(){
        return "With login working";
    }

    @GetMapping("/admin")
    public String adminOnly(){
        return "Admin only working";
    }
}

