package com.minimarket.web.service.impl;

import com.minimarket.web.dto.request.ProductRequest;
import com.minimarket.web.dto.response.ProductResponse;
import com.minimarket.web.model.product.Product;
import com.minimarket.web.repository.ProductRepository;
import com.minimarket.web.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String IMAGE_UPLOAD_DIR = "src/main/resources/static/images/products/";

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getProductName());
        product.setCategory(null); // Set category if applicable
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setImageUrl(null);
        Product savedProduct = productRepository.save(product);
        return mapToResponse(savedProduct);
    }

    @Override
        public ProductResponse addProductWithImage(ProductRequest productRequest, MultipartFile image) {
        Product product = new Product();
        product.setName(productRequest.getProductName());
        product.setCategory(null); // Set category if applicable
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());

        if (!image.isEmpty()) {
                try {
                byte[] bytes = image.getBytes();
                Path path = Paths.get(IMAGE_UPLOAD_DIR + image.getOriginalFilename());
                System.out.println("Saving image to: " + path.toString()); // Debug log
                Files.write(path, bytes);
                product.setImageUrl("/images/products/" + image.getOriginalFilename());
                } catch (IOException e) {
                e.printStackTrace(); // Cetak error ke konsol
                throw new RuntimeException("Failed to save image", e);
                }
        }

        Product savedProduct = productRepository.save(product);
        return mapToResponse(savedProduct);
        }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
        existingProduct.setName(productRequest.getProductName());
        existingProduct.setCategory(null); // Set category if applicable
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setStock(productRequest.getStock());
        existingProduct.setDescription(productRequest.getDescription());

        Product updatedProduct = productRepository.save(existingProduct);
        return mapToResponse(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
        return mapToResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ProductResponse mapToResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getCategory() != null ? product.getCategory().getName() : null,
                product.getPrice(),
                product.getStock(),
                product.getImageUrl()
        );
    }
}
