package com.minimarket.web.controller.web;

import com.minimarket.web.dto.response.ProductResponse;
import com.minimarket.web.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebCustomerController {

    @Autowired
    private ProductService productService;

    @GetMapping("/customer/home")
    public String showHomePage(Model model) {
        return "customer/home";
    }

    @GetMapping("/customer/cart")
    public String showCart(Model model) {
        return "customer/cart/view";
    }

    @GetMapping("/customer/products/list")
    public String showProductList(
            @RequestParam(value = "category", required = false) Long categoryId,
            @RequestParam(value = "sort", required = false) String sort,
            Model model) {
        List<ProductResponse> products = productService.getAllProducts(categoryId, sort);
        model.addAttribute("products", products);
        return "customer/products/list";
    }

    @GetMapping("/customer/products/detail/{id}")
    public String showProductDetail(@PathVariable Long id, Model model) {
        ProductResponse product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "customer/products/detail";
    }
}
