package org.teamone.onlinestorebuyreadreview.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Stanislav Hlova
 */
@Configuration
@Profile("no-security")
public class NoSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(urlConfig -> {
                    urlConfig.anyRequest().permitAll();
                }
        );

        return httpSecurity.build();
    }
}
