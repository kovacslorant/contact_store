package com.example.contact_store.dto.user;

import com.example.contact_store.domain.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateCommand {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String roleType;
    private List<Contact> contacts;


}
