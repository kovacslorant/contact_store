package com.example.contact_store.repository;

import com.example.contact_store.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);
    UserEntity findByEmail(String email);
}
