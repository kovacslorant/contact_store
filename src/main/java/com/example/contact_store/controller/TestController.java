package com.example.contact_store.controller;

import com.example.contact_store.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    @GetMapping("/")
    public String loggedIn(){
        return "Greetings";
    }

    @GetMapping("/secured")
    public String secured(@AuthenticationPrincipal UserPrincipal principal) {
        return "If you see this, you're logged in " + principal.getEmail()
                + "User ID: " + principal.getUserId();
    }
    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal UserPrincipal principal) {
        return "If you see this, you're logged in as Admin " + principal.getEmail()
                + "User ID: " + principal.getUserId();
    }
}
