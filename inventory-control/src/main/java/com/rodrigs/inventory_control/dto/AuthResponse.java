package com.rodrigs.inventory_control.dto;

public record AuthResponse(
        String accessToken,
        String tokenType
) {
}
