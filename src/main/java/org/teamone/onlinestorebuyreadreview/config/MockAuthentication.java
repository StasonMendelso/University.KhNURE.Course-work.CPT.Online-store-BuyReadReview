package org.teamone.onlinestorebuyreadreview.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Stanislav Hlova
 */
public class MockAuthentication implements Authentication {
    private final GrantedAuthority grantedAuthority;

    public MockAuthentication(GrantedAuthority grantedAuthority) {
        this.grantedAuthority = grantedAuthority;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(grantedAuthority.getAuthority()));
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return new User("user1", "pass", Collections.singleton(new SimpleGrantedAuthority(grantedAuthority.getAuthority())));
    }

    @Override
    public Object getPrincipal() {
        return new MockPersonDetails(grantedAuthority.getAuthority());
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
