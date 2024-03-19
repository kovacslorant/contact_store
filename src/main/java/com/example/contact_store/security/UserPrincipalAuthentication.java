package com.example.contact_store.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserPrincipalAuthentication extends AbstractAuthenticationToken {
    private final UserPrincipal principal;
    public UserPrincipalAuthentication(UserPrincipal principal) {
        super(principal.getAuthorities());
        this.principal = principal;
        setAuthenticated(true);

    }

    @Override
    public UserPrincipal getCredentials() {
        return principal;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
