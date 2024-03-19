package com.example.contact_store.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;


public class UserPrincipalAuthentication extends AbstractAuthenticationToken {
    private final UserPrincipal principal;
    public UserPrincipalAuthentication(UserPrincipal principal) {
        super(principal.getAuthorities());
        this.principal = principal;
        setAuthenticated(true);

    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public UserPrincipal getPrincipal() {
        return principal;
    }
}
