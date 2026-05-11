package com.abbtech.notification.dto;

import com.abbtech.notification.model.enums.NotificationStatus;

import java.time.LocalDateTime;

public record ResponseNotificationDto(
        Long id,
        Long userId,
        String userIdText,
        String message,
        NotificationStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime readAt
) {
}
