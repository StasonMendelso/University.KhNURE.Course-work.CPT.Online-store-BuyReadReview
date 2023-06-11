package org.teamone.onlinestorebuyreadreview.security.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.teamone.onlinestorebuyreadreview.database.entity.Role;
import org.teamone.onlinestorebuyreadreview.security.details.NoAuthGuestDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author Stanislav Hlova
 */
public class GuestAuthentication implements Authentication {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Role.GUEST.getName()));
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return new NoAuthGuestDetails();
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalStateException("Guest can't be authenticated! Create a new authentication object.");
    }

    @Override
    public String getName() {
        return NoAuthGuestDetails.class.getName();
    }
}

