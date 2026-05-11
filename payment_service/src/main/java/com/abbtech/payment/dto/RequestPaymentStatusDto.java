package com.abbtech.payment.dto;

import com.abbtech.payment.model.enums.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record RequestPaymentStatusDto(
        @NotNull(message = "Status bos ola bilmez")
        PaymentStatus status
) {
}
