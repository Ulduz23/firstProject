package com.abbtech.payment.dto;

import com.abbtech.payment.model.enums.PaymentMethod;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record RequestPaymentDto(
        @NotNull(message = "User id bos ola bilmez")
        Long userId,
        @NotNull(message = "Amount bos ola bilmez")
        @DecimalMin(value = "0.01", message = "Amount 0.01-den boyuk olmalidir")
        BigDecimal amount,
        @NotNull(message = "Payment method bos ola bilmez")
        PaymentMethod method
) {
}
