package com.abbtech.dto.security;

import lombok.Builder;

@Builder
public record RefreshRequestDto(String refreshToken) {
}
