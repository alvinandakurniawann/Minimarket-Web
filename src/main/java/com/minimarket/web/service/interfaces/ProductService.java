package com.minimarket.web.service.interfaces;

import com.minimarket.web.dto.request.ProductRequest;
import com.minimarket.web.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse addProduct(ProductRequest productRequest);

    ProductResponse updateProduct(Long id, ProductRequest productRequest);

    void deleteProduct(Long id);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);
}
