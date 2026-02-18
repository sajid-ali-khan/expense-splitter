package org.example.expensesplitter.controller;

import org.example.expensesplitter.dtos.CreateUserRequest;
import org.example.expensesplitter.dtos.LoginRequest;
import org.example.expensesplitter.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@AllArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("")
    public String sayHello() {
        return "Hello, World!";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request){
        if (!userService.loginUser(request)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
    
}
