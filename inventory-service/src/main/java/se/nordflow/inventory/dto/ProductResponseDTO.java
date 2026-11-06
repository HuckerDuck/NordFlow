package se.nordflow.inventory.dto;

import se.nordflow.inventory.model.Category;

import java.math.BigDecimal;
import java.util.UUID;

// What the customer sees, reserved stock is not shown
public record ProductResponseDTO(
        UUID id,
        String sku,
        String name,
        Category category,
        BigDecimal price,
        Integer availableQuantity
) {
}
