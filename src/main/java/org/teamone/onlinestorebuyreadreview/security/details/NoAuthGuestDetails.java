package org.teamone.onlinestorebuyreadreview.security.details;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.teamone.onlinestorebuyreadreview.database.entity.Role;

import java.util.Collection;
import java.util.List;

/**
 * @author Stanislav Hlova
 */
public class NoAuthGuestDetails implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Role.GUEST.getName()));
    }

    @Override
    public String getPassword() {
        return "Guest";
    }

    @Override
    public String getUsername() {
        return "Guest";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
