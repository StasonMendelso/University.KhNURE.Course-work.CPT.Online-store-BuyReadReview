package org.teamone.onlinestorebuyreadreview.http.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;
import org.teamone.onlinestorebuyreadreview.config.MockAuthentication;
import org.teamone.onlinestorebuyreadreview.database.entity.Role;

import java.io.IOException;

/**
 * @author Stanislav Hlova
 */
@RequiredArgsConstructor
public class SecurityContextFilter implements Filter {
    private final String authority;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        SecurityContext securityContext = SecurityContextHolder.getContext();

        if (authority.isBlank() || securityContext.getAuthentication() == null) {
            securityContext.setAuthentication(new MockAuthentication(new SimpleGrantedAuthority(Role.GUEST.getName())));
        }else {
            securityContext.setAuthentication(new MockAuthentication(new SimpleGrantedAuthority(Role.getInstance(authority).getName())));
        }

        HttpSession session = ((HttpServletRequest) request).getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, securityContext);
        chain.doFilter(request, response);
    }
}
