package com.abbtech.payment.dto;

import com.abbtech.payment.model.enums.PaymentMethod;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
@Schema(example = """
        {
          "userId": 1,
          "amount": 25.50,
          "method": "CARD"
        }
        """)
public record RequestPaymentDto(
        @NotNull(message = "User id bos ola bilmez")
        @Schema(example = "1")
        Long userId,
        @NotNull(message = "Amount bos ola bilmez")
        @DecimalMin(value = "0.01", message = "Amount 0.01-den boyuk olmalidir")
        @Schema(example = "25.50")
        BigDecimal amount,
        @NotNull(message = "Payment method bos ola bilmez")
        @Schema(example = "CARD")
        PaymentMethod method
) {
}
