package com.example.contact_store.controller;

import com.example.contact_store.dto.contact.ContactCreateCommand;
import com.example.contact_store.service.ContactService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contact")
@Slf4j
@AllArgsConstructor
public class ContactController {
    private final ContactService contactService;


    @PostMapping
    public ResponseEntity saveContact(@RequestBody @Valid ContactCreateCommand command){
        contactService.saveContact(command);
        log.info("New user created");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
