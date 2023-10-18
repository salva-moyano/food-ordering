package com.mpr.order.config;

import com.mpr.order.business.domain.OrderDomainService;
import com.mpr.order.business.domain.OrderDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderServiceConfig {
    @Bean
    public OrderDomainService orderDomainService() {
        return new OrderDomainServiceImpl();
    }
}
