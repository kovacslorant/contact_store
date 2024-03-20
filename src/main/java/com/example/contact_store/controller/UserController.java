package com.example.contact_store.controller;

import com.example.contact_store.dto.user.UserCreateCommand;
import com.example.contact_store.dto.user.UserListItem;
import com.example.contact_store.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity saveUser(@RequestBody @Valid UserCreateCommand command){
        userService.saveUser(command);
        log.info("New user created");
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
    @GetMapping("/list")
    public ResponseEntity<List<UserListItem>> getAllUser() {
        List<UserListItem> allUser = userService.getAllUser();
        log.info("User list page is requested");
        return new ResponseEntity<>(allUser, HttpStatus.OK);

    }

}
