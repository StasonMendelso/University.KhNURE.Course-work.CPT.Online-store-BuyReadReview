package org.teamone.onlinestorebuyreadreview.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.teamone.onlinestorebuyreadreview.http.interceptors.ContextPathInterceptor;
import org.teamone.onlinestorebuyreadreview.http.listener.CartSessionListener;

/**
 * @author Stanislav Hlova
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final CartSessionListener cartSessionListener;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://your-domain.com")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .exposedHeaders("Authorization");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ContextPathInterceptor());
    }
    @Bean
    public ServletListenerRegistrationBean<CartSessionListener> sessionListenerWithMetrics() {
        ServletListenerRegistrationBean<CartSessionListener> listenerRegBean =
                new ServletListenerRegistrationBean<>();

        listenerRegBean.setListener(cartSessionListener);
        return listenerRegBean;
    }
}
