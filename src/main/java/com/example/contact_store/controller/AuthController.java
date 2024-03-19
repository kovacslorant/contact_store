package com.example.contact_store.controller;

import com.example.contact_store.dto.login.LoginRequest;
import com.example.contact_store.dto.login.LoginResponse;
import com.example.contact_store.security.JwtIssuer;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final JwtIssuer jwtIssuer;

    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request){
        var token = jwtIssuer.issue(1L, request.getEmail(),"USER");
    return LoginResponse.builder()
            .accessToken(token)
            .build();
    }
}
