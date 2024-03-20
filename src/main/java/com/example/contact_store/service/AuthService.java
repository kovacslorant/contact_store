package com.example.contact_store.service;

import com.example.contact_store.domain.UserEntity;
import com.example.contact_store.dto.login.LoginResponse;
import com.example.contact_store.security.JwtIssuer;
import com.example.contact_store.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class AuthService {

    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

@Autowired
    public AuthService(JwtIssuer jwtIssuer, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserService userService) {
        this.jwtIssuer = jwtIssuer;
        this.authenticationManager = authenticationManager;
    this.passwordEncoder = passwordEncoder;
    this.userService = userService;
}

    public LoginResponse attemptLogin(String email, String password) {

        UserEntity user = userService.findByEmail(email);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Wrong e-mail or password");
        }

        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var principal = (UserPrincipal) authentication.getPrincipal();

        var role = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        var token = jwtIssuer.issue(principal.getUserId(), principal.getEmail(), role);
        return LoginResponse.builder()
                .accessToken(token)
                .build();
    }
}
