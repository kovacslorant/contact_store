package com.example.contact_store.service;

import com.example.contact_store.domain.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final String EXISTING_EMAIL = "test@test.com";
    public Optional<UserEntity> findByEmail(String email){
        if(! EXISTING_EMAIL.equalsIgnoreCase(email)) return Optional.empty();

        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setEmail(EXISTING_EMAIL);
        user.setPassword("$2a$12$Ko51fanFSMmMnw/xXQqvFepfYiLRxF.qeeUVnI.evsnKVCd8.4FxK");
        user.setFirstName("Teszt");
        user.setLastName("Aladár");
        user.setRoleType("ROLE_USER");

        return Optional.of(user);
    }

}
