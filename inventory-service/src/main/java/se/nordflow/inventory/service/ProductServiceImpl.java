package se.nordflow.inventory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.nordflow.inventory.dto.ProductResponseDTO;
import se.nordflow.inventory.exception.ProductNotFoundException;
import se.nordflow.inventory.model.Category;
import se.nordflow.inventory.model.Product;
import se.nordflow.inventory.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<ProductResponseDTO> findAllProducts(Category category) {
        // No category means show everything
        List<Product> products = category == null
                ? productRepository.findAll()
                : productRepository.findByCategory(category);

        return products.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public ProductResponseDTO findProductById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return toResponseDTO(product);
    }

    private ProductResponseDTO toResponseDTO(Product product) {
        //? What can actually be bought right now
        int availableQuantity = product.getStockQuantity() - product.getReservedQuantity();

        return new ProductResponseDTO(
                product.getId(),
                product.getSku(),
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                availableQuantity
        );
    }
}
