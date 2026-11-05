package se.nordflow.inventory.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "products")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String sku;
    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    // BigDecimal and not double, double gives rounding errors on money
    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    //? Stock that is held by orders that are not confirmed yet
    @Column(name = "reserved_quantity")
    private Integer reservedQuantity;

    @Column(name = "created_at")
    private LocalDateTime createdDate;

    @Column(name = "updated_at")
    private LocalDateTime updatedDate;

    @PrePersist
    private void prePersist() {
        createdDate = LocalDateTime.now();
        updatedDate = LocalDateTime.now();

        if (reservedQuantity == null) {
            reservedQuantity = 0;
        }
    }

    @PreUpdate
    private void preUpdate() {
        updatedDate = LocalDateTime.now();
    }
}
