package org.teamone.onlinestorebuyreadreview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.teamone.onlinestorebuyreadreview.database.entity.User;
import org.teamone.onlinestorebuyreadreview.database.repository.UserRepository;
import org.teamone.onlinestorebuyreadreview.security.details.AuthUserDetails;

import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Користувача не знайдено.");
        }

        return new AuthUserDetails(user.get());
    }
}
