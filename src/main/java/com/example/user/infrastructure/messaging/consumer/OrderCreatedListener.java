package com.example.user.infrastructure.messaging.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.example.user.infrastructure.messaging.config.RabbitConfig;
import com.example.user.infrastructure.messaging.events.OrderCreatedEvent;

@Service
public class OrderCreatedListener {

    @RabbitListener(queues = RabbitConfig.USER_QUEUE)
    public void handleOrderCreated(OrderCreatedEvent event) {
        System.out.println("USER-SERVICE recibió evento: " + event.getOrderId());

    }
}


