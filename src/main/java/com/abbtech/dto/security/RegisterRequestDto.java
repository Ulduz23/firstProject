package com.abbtech.dto.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RegisterRequestDto(
        @NotBlank(message = "Username bos ola bilmez")
        @Size(min = 3, max = 50, message = "Username 3 ile 50 simvol arasinda olmalidir")
        String username,
        @NotBlank(message = "Full name bos ola bilmez")
        @Size(min = 3, max = 100, message = "Full name 3 ile 100 simvol arasinda olmalidir")
        String fullName,
        @NotBlank(message = "Email bos ola bilmez")
        @Email(message = "Email formati yanlisdir")
        String email,
        @NotBlank(message = "Password bos ola bilmez")
        @Size(min = 5, max = 100, message = "Password 5 ile 100 simvol arasinda olmalidir")
        String password
) {}
