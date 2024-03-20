package com.example.contact_store.dto.user;

import com.example.contact_store.domain.Address;
import com.example.contact_store.domain.Contact;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class UserListItem {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String roleType;
    private List<Contact> contacts = new ArrayList<>();
}
