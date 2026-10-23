package com.rodrigs.inventory_control.dto;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        BigDecimal price,
        Integer quantity
) {
}
