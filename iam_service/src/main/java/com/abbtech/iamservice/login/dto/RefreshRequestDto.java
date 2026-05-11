package com.abbtech.iamservice.login.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RefreshRequestDto(
        @NotBlank(message = "Refresh token bos ola bilmez")
        String refreshToken
) {
}
