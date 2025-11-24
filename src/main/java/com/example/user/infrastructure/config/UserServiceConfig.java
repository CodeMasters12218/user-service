package com.example.user.infrastructure.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceConfig {

    @Bean
    public Queue productQueue() {
        return new Queue("product.created.queue", true);
    }
}

