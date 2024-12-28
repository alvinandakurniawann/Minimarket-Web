package com.minimarket.web.service.interfaces;

import com.minimarket.web.dto.request.ProductRequest;
import com.minimarket.web.dto.response.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    ProductResponse addProduct(ProductRequest productRequest);

    ProductResponse addProductWithImage(ProductRequest productRequest, MultipartFile image);

    ProductResponse updateProduct(Long id, ProductRequest productRequest);

    void deleteProduct(Long id);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);
}
