package com.example.contact_store.controller;

import com.example.contact_store.dto.login.LoginRequest;
import com.example.contact_store.dto.login.LoginResponse;
import com.example.contact_store.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Validated LoginRequest request){
        return new ResponseEntity<>(authService.attemptLogin(request.getEmail(), request.getPassword()), HttpStatus.OK);
    }
}
