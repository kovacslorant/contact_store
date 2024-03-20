package com.example.contact_store.dto.login;

import com.example.contact_store.dto.user.UserDetailsItem;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private final UserDetailsItem userDetailsItem;
    private final String accessToken;

}
