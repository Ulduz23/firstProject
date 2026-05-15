package com.abbtech.notification.dto;

import com.abbtech.notification.model.enums.NotificationStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
@Schema(example = """
        {
          "status": "READ"
        }
        """)
public record RequestNotificationStatusDto(
        @NotNull(message = "Status bos ola bilmez")
        @Schema(example = "READ")
        NotificationStatus status
) {
}
