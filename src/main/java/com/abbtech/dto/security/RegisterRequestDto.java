package com.abbtech.dto.security;

import lombok.Builder;

@Builder
public record RegisterRequestDto(
        String username,
        String fullName,
        String email,
        String password
) {}
