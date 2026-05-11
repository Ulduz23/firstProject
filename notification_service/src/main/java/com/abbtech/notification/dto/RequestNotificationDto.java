package com.abbtech.notification.dto;

import com.abbtech.notification.model.enums.NotificationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RequestNotificationDto(
        @NotNull(message = "User id bos ola bilmez")
        Long userId,
        @NotBlank(message = "Message bos ola bilmez")
        @Size(max = 1000, message = "Message maksimum 1000 simvol ola biler")
        String message,
        NotificationStatus status
) {
}
