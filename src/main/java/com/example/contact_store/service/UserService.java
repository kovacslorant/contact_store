package com.example.contact_store.service;

import com.example.contact_store.domain.Address;
import com.example.contact_store.domain.Contact;
import com.example.contact_store.domain.UserEntity;
import com.example.contact_store.dto.user.UserCreateCommand;
import com.example.contact_store.dto.user.UserListItem;
import com.example.contact_store.repository.UserRepository;
import com.example.contact_store.security.UserPrincipal;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username);
        return UserPrincipal.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .authorities(List.of(new SimpleGrantedAuthority(user.getRoleType())))
                .password(user.getPassword())
                .build();
    }


    public void saveUser(UserCreateCommand command) {
        if (userRepository.existsByEmail(command.getEmail())) {
            throw new IllegalStateException("There is an account with that email address!");
        }
        UserEntity user = mapUserDtoToEntity(command);
        userRepository.save(user);

    }

    private UserEntity mapUserDtoToEntity(UserCreateCommand command) {

        UserEntity user = new UserEntity();
        user.setFirstName(command.getFirstName());
        user.setLastName(command.getLastName());
        user.setEmail(command.getEmail());
        user.setPassword(passwordEncoder.encode(command.getPassword()));
        user.setRoleType("ROLE_USER");

        return user;
    }

    public List<UserListItem> getAllUser() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                .map(this::mapUserListItemToEntity)
                .collect(Collectors.toList());
    }

    private UserListItem mapUserListItemToEntity(UserEntity userEntity) {

        UserListItem userListItem = new UserListItem();
        userListItem.setId(userEntity.getId());
        userListItem.setFirstName(userEntity.getFirstName());
        userListItem.setLastName(userEntity.getLastName());
        userListItem.setEmail(userEntity.getEmail());
        userListItem.setRoleType(userEntity.getRoleType());
        return userListItem;
    }
}
