package com.abbtech.notification.dto;

import com.abbtech.notification.model.enums.NotificationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record RequestNotificationStatusDto(
        @NotNull(message = "Status bos ola bilmez")
        NotificationStatus status
) {
}
