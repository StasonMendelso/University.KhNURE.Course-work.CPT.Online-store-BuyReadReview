package org.teamone.onlinestorebuyreadreview.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.teamone.onlinestorebuyreadreview.security.details.AuthUserDetails;

import java.util.Collection;

/**
 * @author Stanislav Hlova
 */
@RequiredArgsConstructor
public class UserAuthentication implements Authentication {
    private final AuthUserDetails userDetails;
    private boolean authenticated = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return userDetails.getPassword();
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userDetails;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return AuthUserDetails.class.getName();
    }
}
