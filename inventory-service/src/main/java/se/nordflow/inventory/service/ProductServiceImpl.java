package se.nordflow.inventory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.nordflow.inventory.dto.ProductRequestDTO;
import se.nordflow.inventory.dto.ProductResponseDTO;
import se.nordflow.inventory.exception.ProductNotFoundException;
import se.nordflow.inventory.exception.SkuAlreadyExistsException;
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

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) {
        if (productRepository.existsBySku(requestDTO.sku())) {
            throw new SkuAlreadyExistsException(requestDTO.sku());
        }

        Product product = new Product();
        product.setSku(requestDTO.sku());
        product.setName(requestDTO.name());
        product.setCategory(requestDTO.category());
        product.setPrice(requestDTO.price());
        product.setStockQuantity(requestDTO.stockQuantity());

        Product savedProduct = productRepository.save(product);
        return toResponseDTO(savedProduct);
    }

    @Override
    public ProductResponseDTO updateProduct(UUID id, ProductRequestDTO requestDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        //? Only check the SKU if it is changed, otherwise it would find itself
        boolean skuIsChanged = !product.getSku().equals(requestDTO.sku());
        if (skuIsChanged && productRepository.existsBySku(requestDTO.sku())) {
            throw new SkuAlreadyExistsException(requestDTO.sku());
        }

        product.setSku(requestDTO.sku());
        product.setName(requestDTO.name());
        product.setCategory(requestDTO.category());
        product.setPrice(requestDTO.price());
        product.setStockQuantity(requestDTO.stockQuantity());

        Product savedProduct = productRepository.save(product);
        return toResponseDTO(savedProduct);
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
