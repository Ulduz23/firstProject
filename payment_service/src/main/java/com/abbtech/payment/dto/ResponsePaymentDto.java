package com.abbtech.payment.dto;

import com.abbtech.payment.model.enums.PaymentMethod;
import com.abbtech.payment.model.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ResponsePaymentDto(
        Long id,
        Long userId,
        String userIdText,
        BigDecimal amount,
        PaymentMethod method,
        PaymentStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
