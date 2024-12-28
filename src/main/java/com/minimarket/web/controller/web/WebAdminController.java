package com.minimarket.web.controller.web;

import com.minimarket.web.dto.request.ProductRequest;
import com.minimarket.web.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class WebAdminController {

    @Autowired
    private ProductService productService;

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        return "admin/dashboard";
    }

    @GetMapping("/admin/product/list")
    public String manageProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin/products/list";
    }

    @GetMapping("/admin/product/add")
    public String addProductPage(Model model) {
        model.addAttribute("productRequest", new ProductRequest());
        return "admin/products/add";
    }

    @PostMapping("/admin/product/add")
    public String addProduct(@ModelAttribute ProductRequest productRequest, 
                             @RequestParam("image") MultipartFile image) {
        productService.addProductWithImage(productRequest, image);
        return "redirect:/admin/product/list";
    }
}
