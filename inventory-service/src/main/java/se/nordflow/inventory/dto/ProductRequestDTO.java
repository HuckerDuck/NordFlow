package se.nordflow.inventory.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import se.nordflow.inventory.model.Category;

import java.math.BigDecimal;

public record ProductRequestDTO(
        @NotBlank(message = "SKU is needed and cannot be blank")
        @Size(max = 50, message = "SKU can be max 50 characters")
        String sku,

        @NotBlank(message = "Name is needed and cannot be blank")
        @Size(max = 150, message = "Name can be max 150 characters")
        String name,

        @NotNull(message = "Category is needed")
        Category category,

        // Same as NUMERIC(10, 2) in the database
        @NotNull(message = "Price is needed")
        @DecimalMin(value = "0.00", message = "Price cannot be negative")
        @Digits(integer = 8, fraction = 2, message = "Price can have max 2 decimals")
        BigDecimal price,

        @NotNull(message = "Stock quantity is needed")
        @Min(value = 0, message = "Stock quantity cannot be negative")
        Integer stockQuantity
) {
}
