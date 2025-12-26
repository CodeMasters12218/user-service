package com.example.user.application;

import org.springframework.stereotype.Service;

import com.example.user.infrastructure.repository.UserEntity;

@Service
public class NotificationService {

    public void sendOrderCreatedNotification(UserEntity user, String orderId) {
        System.out.println("Notificando al usuario " + user.getEmail()
                + " sobre el pedido " + orderId);
    }
}
