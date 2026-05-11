package com.abbtech.payment.client.notifications;

import com.abbtech.payment.client.notifications.enums.NotificationStatus;

import java.time.LocalDateTime;

public record ResponseNotificationDto(
        Long id,
        Long userId,
        String username,
        String message,
        NotificationStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime readAt
) {
}
