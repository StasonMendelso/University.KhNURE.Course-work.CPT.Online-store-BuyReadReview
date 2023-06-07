package org.teamone.onlinestorebuyreadreview.http.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Cart;
import org.teamone.onlinestorebuyreadreview.service.CartService;
import org.teamone.onlinestorebuyreadreview.util.constant.SessionConstant;

/**
 * @author Stanislav Hlova
 */
@WebListener
@Component
@RequiredArgsConstructor
public class CartSessionListener implements HttpSessionListener {
    private final CartService cartService;
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        Cart cart = (Cart) session.getAttribute(SessionConstant.CART);
        if(cart!=null && cart.getId()!=null){
            cartService.updateCart(cart);
        }
    }
}
