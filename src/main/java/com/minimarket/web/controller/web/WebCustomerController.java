package com.minimarket.web.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebCustomerController {

    @GetMapping("/customer/home")
    public String showHomePage(Model model) {
        return "customer/home"; // Mengarah ke src/main/resources/templates/customer/home.html
    }

    @GetMapping("/customer/cart")
    public String showCart(Model model) {
        return "customer/cart/view"; // Mengarah ke src/main/resources/templates/customer/cart/view.html
    }

    @GetMapping("/customer/products")
    public String showProductList(Model model) {
        return "customer/products/list"; // Mengarah ke src/main/resources/templates/customer/products/list.html
    }
}
