package com.abbtech.notification.dto;

import com.abbtech.notification.model.enums.NotificationStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(example = """
        {
          "userId": 1,
          "message": "Order payment completed successfully",
          "status": "NEW"
        }
        """)
public record RequestNotificationDto(
        @NotNull(message = "User id bos ola bilmez")
        @Schema(example = "1")
        Long userId,
        @NotBlank(message = "Message bos ola bilmez")
        @Size(max = 1000, message = "Message maksimum 1000 simvol ola biler")
        @Schema(example = "Order payment completed successfully")
        String message,
        @Schema(example = "NEW")
        NotificationStatus status
) {
}
