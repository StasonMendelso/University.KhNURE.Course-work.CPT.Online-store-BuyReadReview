package org.teamone.onlinestorebuyreadreview.config;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;
import org.teamone.onlinestorebuyreadreview.config.properties.NoSecurityProperties;

/**
 * @author Stanislav Hlova
 */
@Configuration
@Profile("dev")
@AllArgsConstructor
public class NoSecurityConfig {

    private final NoSecurityProperties noSecurityProperties;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(urlConfig -> {
                    urlConfig.anyRequest().permitAll();
                }
        );

        return httpSecurity.build();
    }

    @Bean
    public Filter securityContextFilter() {
        System.out.println("from securityproperties : " + noSecurityProperties.getAuthority());
        return (request, response, chain) -> {
            SecurityContext securityContext = SecurityContextHolder.getContext();

            if (noSecurityProperties.getAuthority().isBlank()) {
                securityContext.setAuthentication(new MockAuthentication(new SimpleGrantedAuthority("GUEST")));
            }else {
                securityContext.setAuthentication(new MockAuthentication(new SimpleGrantedAuthority(noSecurityProperties.getAuthority())));
            }

            HttpSession session = ((HttpServletRequest) request).getSession(true);
            session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, securityContext);
            chain.doFilter(request, response);
        };
    }
}
