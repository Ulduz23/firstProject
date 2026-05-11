package com.abbtech.payment.common.exception;

import java.time.LocalDateTime;

public record BaseErrorResponseDTO(
        String message,
        int status,
        LocalDateTime timestamp
) {
}
