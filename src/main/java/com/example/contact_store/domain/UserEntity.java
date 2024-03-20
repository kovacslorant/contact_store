package com.example.contact_store.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    @Email
    private String email;

    @Column
    @JsonIgnore
    private String password;

    @Column
    private String roleType;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
    fetch = FetchType.LAZY)
    private List<Contact> contacts = new ArrayList<>();






}
