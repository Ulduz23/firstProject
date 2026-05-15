package com.abbtech.payment.dto;

import com.abbtech.payment.model.enums.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
@Schema(example = """
        {
          "status": "PAID"
        }
        """)
public record RequestPaymentStatusDto(
        @NotNull(message = "Status bos ola bilmez")
        @Schema(example = "PAID")
        PaymentStatus status
) {
}
