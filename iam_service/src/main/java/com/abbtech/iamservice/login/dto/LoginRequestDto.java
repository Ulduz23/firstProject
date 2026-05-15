package com.abbtech.iamservice.login.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
@Schema(example = """
        {
          "username": "testuser",
          "password": "12345"
        }
        """)
public record LoginRequestDto(
        @NotBlank(message = "Username bos ola bilmez")
        @Schema(example = "testuser")
        String username,
        @NotBlank(message = "Password bos ola bilmez")
        @Schema(example = "12345")
        String password
) {}
