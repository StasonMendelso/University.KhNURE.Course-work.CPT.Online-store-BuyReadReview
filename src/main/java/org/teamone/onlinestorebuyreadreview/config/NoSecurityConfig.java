package org.teamone.onlinestorebuyreadreview.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Stanislav Hlova
 */
@Configuration
@Profile("dev")
@AllArgsConstructor
public class NoSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(urlConfig -> {
                    urlConfig
                            .requestMatchers(HttpMethod.GET, "/books/new").authenticated()
                            .requestMatchers("/login").access((authentication, object) -> new AuthorizationDecision(!authentication.get().isAuthenticated()))
                            .anyRequest().permitAll();
                })
                .formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

}
