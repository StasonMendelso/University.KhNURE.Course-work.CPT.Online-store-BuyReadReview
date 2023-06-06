package org.teamone.onlinestorebuyreadreview.config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.teamone.onlinestorebuyreadreview.config.properties.NoSecurityProperties;
import org.teamone.onlinestorebuyreadreview.http.filter.CartFilter;
import org.teamone.onlinestorebuyreadreview.http.filter.SecurityContextFilter;
import org.teamone.onlinestorebuyreadreview.service.CartService;

/**
 * @author Stanislav Hlova
 */
@Configuration
@RequiredArgsConstructor
public class FilterConfig {
    private final NoSecurityProperties noSecurityProperties;
    private final CartService cartService;

    @Bean
    @Order(value = 1)
    public Filter securityContextFilter() {
        return new SecurityContextFilter(noSecurityProperties.getAuthority());
    }
    @Bean
    @Order(value = 2)
    public Filter cartFilter(){
        return new CartFilter(cartService);
    }
}
