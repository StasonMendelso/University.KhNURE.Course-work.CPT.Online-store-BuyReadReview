package org.teamone.onlinestorebuyreadreview.http.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.teamone.onlinestorebuyreadreview.database.entity.Cart;
import org.teamone.onlinestorebuyreadreview.database.entity.Role;
import org.teamone.onlinestorebuyreadreview.database.entity.User;
import org.teamone.onlinestorebuyreadreview.security.details.AuthUserDetails;
import org.teamone.onlinestorebuyreadreview.service.CartService;
import org.teamone.onlinestorebuyreadreview.util.constant.SessionConstant;

import java.io.IOException;

/**
 * @author Stanislav Hlova
 */
@RequiredArgsConstructor
public class CartFilter implements Filter {
    private final CartService cartService;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession httpSession = httpServletRequest.getSession();
        Cart cart = (Cart) httpSession.getAttribute(SessionConstant.CART);

        if(cart == null){
            SecurityContext securityContext = SecurityContextHolder.getContext();
            Role currentRole = securityContext.getAuthentication().getAuthorities()
                    .stream()
                    .findFirst()
                    .map(GrantedAuthority::getAuthority)
                    .map(Role::getInstance)
                    .orElseThrow(() -> new RuntimeException("No role was defined. Please, define a role before this filter."));
            if(currentRole.equals(Role.GUEST)){
                httpSession.setAttribute(SessionConstant.CART,new Cart());
            }
            if(currentRole.equals(Role.CLIENT)){
                AuthUserDetails authUserDetails = (AuthUserDetails) securityContext.getAuthentication().getPrincipal();
                User user = authUserDetails.getUser();
//                httpSession.setAttribute(SessionConstant.CART,cartService.getCartByClientId(user.getId()));
                httpSession.setAttribute(SessionConstant.CART,new Cart());
            }
        }
        chain.doFilter(request,response);
    }
}
