package com.abbtech.iamservice.login.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record LoginRequestDto(
        @NotBlank(message = "Username bos ola bilmez")
        String username,
        @NotBlank(message = "Password bos ola bilmez")
        String password
) {}
