package com.mpr.order.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
class WebConfig {

    @Bean
    @ConditionalOnProperty("enable-http-traces")
    public WebMvcConfigurer httpLogger() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(@NonNull InterceptorRegistry registry) {
                registry.addInterceptor(HANDLER_INTERCEPTOR);
            }
        };
    }

    private static final HandlerInterceptor HANDLER_INTERCEPTOR = new HandlerInterceptor() {
        @Override
        public void afterCompletion(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @NonNull Object handler,
                                    @Nullable Exception ex) {
            String params = request.getQueryString() != null ? "?" + request.getQueryString() : "";
            log.info("[{}][{}][{}{}{}]",
                     request.getMethod(),
                     response.getStatus(),
                     request.getRemoteAddr(),
                     request.getRequestURI(),
                     params);
        }
    };
}