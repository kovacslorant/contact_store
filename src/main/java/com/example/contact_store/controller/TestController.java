package com.example.contact_store.controller;

import lombok.RequiredArgsConstructor;
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
    public String secured() {
        return "If you see this, you're logged in";
    }
}
