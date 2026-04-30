package com.abbtech.dto.security;

import lombok.Builder;

@Builder
public record LoginRequestDto(
        String username,
        String password
) {}
