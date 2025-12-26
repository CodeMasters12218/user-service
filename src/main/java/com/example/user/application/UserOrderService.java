package com.example.user.application;

import org.springframework.stereotype.Service;

import com.example.user.domain.model.User;
import com.example.user.infrastructure.messaging.events.OrderCreatedEvent;
import com.example.user.infrastructure.repository.JpaUserRepository;
import com.example.user.infrastructure.repository.PurchaseHistoryEntity;
import com.example.user.infrastructure.repository.PurchaseHistoryRepository;
import com.example.user.infrastructure.repository.UserEntity;

@Service
public class UserOrderService {

    private final JpaUserRepository userRepository;
    private final PurchaseHistoryRepository historyRepository;
    private final NotificationService notificationService;

    public UserOrderService(JpaUserRepository userRepository,
                            PurchaseHistoryRepository historyRepository,
                            NotificationService notificationService) {
        this.userRepository = userRepository;
        this.historyRepository = historyRepository;
        this.notificationService = notificationService;
    }

    public void processOrder(OrderCreatedEvent event) {

        Integer userId = Integer.valueOf(event.getUserId());

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + userId));

        PurchaseHistoryEntity history = new PurchaseHistoryEntity(
                user.getId(),
                event.getOrderId(),
                event.getTotalAmount(),
                event.getCreatedAt()
        );

        historyRepository.save(history);

        int earnedPoints = (int) Math.floor(event.getTotalAmount() / 10);
        if (user.getPoints() == null) {
            user.setPoints(0);
        }
        user.setPoints(user.getPoints() + earnedPoints);
        userRepository.save(user); // UserEntity

        notificationService.sendOrderCreatedNotification(user, event.getOrderId());
    }
}
