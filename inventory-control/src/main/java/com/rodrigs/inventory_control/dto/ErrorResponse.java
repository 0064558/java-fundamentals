package com.rodrigs.inventory_control.dto;

public record ErrorResponse(
        int status,
        String error,
        String message
) {
}
