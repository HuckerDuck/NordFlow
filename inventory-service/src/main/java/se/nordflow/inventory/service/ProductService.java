package se.nordflow.inventory.service;

import se.nordflow.inventory.dto.ProductRequestDTO;
import se.nordflow.inventory.dto.ProductResponseDTO;
import se.nordflow.inventory.model.Category;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<ProductResponseDTO> findAllProducts(Category category);
    ProductResponseDTO findProductById(UUID id);
    ProductResponseDTO createProduct(ProductRequestDTO requestDTO);
    ProductResponseDTO updateProduct(UUID id, ProductRequestDTO requestDTO);
}
