package com.abbtech.dto.security;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record LoginRequestDto(
        @NotBlank(message = "Username bos ola bilmez")
        String username,
        @NotBlank(message = "Password bos ola bilmez")
        String password
) {}
