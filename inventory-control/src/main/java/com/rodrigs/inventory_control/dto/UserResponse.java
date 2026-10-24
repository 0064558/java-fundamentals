package com.rodrigs.inventory_control.dto;

import com.rodrigs.inventory_control.enums.Role;

public record UserResponse(
        Long id,
        String email,
        Role role
) {
}
