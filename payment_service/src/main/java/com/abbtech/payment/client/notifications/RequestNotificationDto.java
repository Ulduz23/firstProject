package com.abbtech.payment.client.notifications;

import com.abbtech.payment.client.notifications.enums.NotificationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record RequestNotificationDto(
        @NotNull String userId,
        @NotBlank String message,
        NotificationStatus status
) {
}
