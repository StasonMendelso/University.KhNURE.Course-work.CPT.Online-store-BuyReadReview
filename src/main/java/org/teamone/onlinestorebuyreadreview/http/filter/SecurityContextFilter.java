package org.teamone.onlinestorebuyreadreview.http.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;
import org.teamone.onlinestorebuyreadreview.database.entity.Role;
import org.teamone.onlinestorebuyreadreview.database.entity.User;
import org.teamone.onlinestorebuyreadreview.security.auth.GuestAuthentication;
import org.teamone.onlinestorebuyreadreview.security.auth.UserAuthentication;
import org.teamone.onlinestorebuyreadreview.security.details.AuthUserDetails;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author Stanislav Hlova
 */
@RequiredArgsConstructor
public class SecurityContextFilter implements Filter {
    private final String authority;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        SecurityContext securityContext = SecurityContextHolder.getContext();

        if ((authority.isBlank() && securityContext.getAuthentication() == null) ||
            ((authority.isBlank() && securityContext.getAuthentication().getClass().equals(AnonymousAuthenticationToken.class)))) {
            securityContext.setAuthentication(new GuestAuthentication());
        } else if (securityContext.getAuthentication() == null || securityContext.getAuthentication().getClass().equals(AnonymousAuthenticationToken.class)) {
            if(authority.equals(Role.GUEST.getName())){
                securityContext.setAuthentication(new GuestAuthentication());
            }else {
                User mockUser = User.builder()
                        .id(10L)
                        .firstName("Mock")
                        .lastName("Ito")
                        .middleName("Uchi")
                        .blocked(false)
                        .invalid(false)
                        .createdAt(LocalDateTime.now())
                        .password("1234")
                        .role(Role.getInstance(authority))
                        .telephoneNumber("+380 (50) 123-12-12")
                        .build();
                securityContext.setAuthentication(new UserAuthentication(new AuthUserDetails(mockUser)));
            }
        }


        HttpSession session = ((HttpServletRequest) request).getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, securityContext);
        chain.doFilter(request, response);
    }
}
