package se.nordflow.inventory.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.nordflow.inventory.dto.ProductRequestDTO;
import se.nordflow.inventory.dto.ProductResponseDTO;
import se.nordflow.inventory.model.Category;
import se.nordflow.inventory.service.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAllProducts(@RequestParam(required = false) Category category) {
        List<ProductResponseDTO> products = productService.findAllProducts(category);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findProductById(@PathVariable UUID id) {
        ProductResponseDTO product = productService.findProductById(id);
        return ResponseEntity.ok(product);
    }

    //? Only ADMIN can add products, a USER token gives 403
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO requestDTO) {
        ProductResponseDTO product = productService.createProduct(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable UUID id,
                                                            @Valid @RequestBody ProductRequestDTO requestDTO) {
        ProductResponseDTO product = productService.updateProduct(id, requestDTO);
        return ResponseEntity.ok(product);
    }
}
