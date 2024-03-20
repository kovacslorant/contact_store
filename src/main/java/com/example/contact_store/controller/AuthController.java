package com.example.contact_store.controller;

import com.example.contact_store.dto.login.LoginRequest;
import com.example.contact_store.dto.login.LoginResponse;
import com.example.contact_store.security.JwtIssuer;
import com.example.contact_store.security.UserPrincipal;
import com.example.contact_store.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request){
        return authService.attemptLogin(request.getEmail(), request.getPassword());
    }
}
