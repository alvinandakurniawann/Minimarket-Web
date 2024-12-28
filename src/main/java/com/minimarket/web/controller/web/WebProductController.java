package com.minimarket.web.controller.web;

import com.minimarket.web.dto.request.ProductRequest;
import com.minimarket.web.dto.response.ProductResponse;
import com.minimarket.web.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class WebProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String listProducts(Model model) {
        List<ProductResponse> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products/list"; // Template HTML: src/main/resources/templates/products/list.html
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new ProductRequest());
        return "products/add"; // Template HTML: src/main/resources/templates/products/add.html
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute ProductRequest productRequest) {
        productService.addProduct(productRequest);
        return "redirect:/products";
    }
}
