package com.minimarket.web.service.interfaces;

import com.minimarket.web.dto.request.ProductRequest;
import com.minimarket.web.dto.response.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    ProductResponse addProduct(ProductRequest productRequest);

    ProductResponse updateProduct(Long id, ProductRequest productRequest);

    ProductResponse addProductWithImage(ProductRequest productRequest, MultipartFile image);

    ProductResponse updateProductWithImage(Long id, ProductRequest productRequest, MultipartFile image);

    ProductResponse getProductById(Long id);

    List<ProductResponse> getAllProducts(); // Overload tanpa parameter

    List<ProductResponse> getAllProducts(Long categoryId, String sort); // Dengan filter dan sort

    void deleteProduct(Long id);
}

